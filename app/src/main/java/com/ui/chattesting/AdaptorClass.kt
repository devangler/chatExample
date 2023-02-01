package com.ui.chattesting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ui.chattesting.databinding.Item2Binding
import com.ui.chattesting.databinding.ItemBinding


class AdaptorClass(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<User, RecyclerView.ViewHolder>(DiffUtilsEmployees) {


    object DiffUtilsEmployees : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class CustomViewHolderEmployee1(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: User) {
            binding.textTxt.text = "${position.id}"
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].left
    }

    class CustomViewHolderEmployee2(private val bindingItem2Binding: Item2Binding) :
        RecyclerView.ViewHolder(bindingItem2Binding.root) {
        fun bind(position: User) {

            bindingItem2Binding.textText2.text = "${position.id}"

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        /*val binding = DataBindingUtil.inflate<ItemBinding>(layoutInflater,
            R.layout.item,
            parent,
            false)
        CustomViewHolderEmployee1(binding)
        return CustomViewHolderEmployee1(binding)
*/
        return if (itemType==1) {

            val binding = DataBindingUtil.inflate<ItemBinding>(layoutInflater,
                R.layout.item,
                parent,
                false)
            CustomViewHolderEmployee1(binding)

        } else {

            val binding2 = DataBindingUtil.inflate<Item2Binding>(layoutInflater,
                R.layout.item2,
                parent,
                false)
            CustomViewHolderEmployee2(binding2)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        //(holder as CustomViewHolderEmployee1).bind(position)
        if (holder.itemViewType == 1) {
            (holder as CustomViewHolderEmployee1).bind(item)
            //VIEW_TYPE=+1
        } else {
            (holder as CustomViewHolderEmployee2).bind(item)
        }


    }
}