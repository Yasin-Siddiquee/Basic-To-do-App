package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter (
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(private val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvTodoTitle.text = todo.title
            binding.cbDone.isChecked = todo.isChecked
            toggleStrikeThrough(binding.tvTodoTitle, todo.isChecked)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(binding.tvTodoTitle, isChecked)
                todo.isChecked = !todo.isChecked
            }
        }

        private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
            if(isChecked){
                tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            }else{
                tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.bind(curTodo)

    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }




    }




