package n7.myperfectemptyproject.ui.main.domain.vo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import n7.myperfectemptyproject.BR

class UserVO : BaseObservable() {

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }

    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var date: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }

    @get:Bindable
    var pictureUrl: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.pictureUrl)
        }
}
