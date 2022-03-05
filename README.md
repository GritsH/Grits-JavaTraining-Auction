# Auction
**Project in front of you is a console application for auctions. Basically, the main idea was somewhat like this (please, bare with me):**
1. Imagine you are a simple **_user_**
   - When you start an application for the first time, the menu which consists of `Log in`, `Sign up` and `Exit` commands is in front of you.
        - If you are new to this app you can firstly `Sign up` (create an account) and then `Log in`
        - If you choose `Exit` then the program will stop due to obvious reasons (you choosed to exit application, duh)
   - Let's pretend you logged in. The next menu you are going to see consists of various commands which allow you to manage your items. If you don't have any, you can always add one. 
   - Let's think you have some spare money and you are ready to spend them on something fancy. You can see all items which are avaliable on this app. If you found something you like, you can _raise your card_ (basically offer your price). But remember, that the price you offer must be higher then the current price. How much higher you may ask. Every lot has its `auction step`. It's an amount of money by which you can raise the price. No more, no less. If the price you offered is the higher one, you bacome a **lot leader**. If nobody dares to change the current price within some time, item becomes yours. After the lot is closed and you are the winner, the contract between you and item's owner is signed. Congrats!!
   - Now, let's think you don't have any money. You can sell your items and earn some cash. For this, you need to `add item` first. Then create a lot. Remeber, when you set a starting price and an action step be reasonable. Nobody wants to buy junk for big money. And all you have to do is wait. 
2. Imagine you are an **_admin_**
   - You can `get users' information` because you have some privileges.  
   - You can also `get information about any specific item`.
   - But you cannot delete any users or items. You are not that powerful.

## How to run
Actually, all you need to do is to start main method. This method will call method `startMenu` from class `Menu`. And this method will initialize imitated database, services and call methods from controllers in while loop. 

## Now, lets talk about reality 
~~Idea is good, implementation is not so good.~~ I'm always trying to be honest with myself, so i think i've done a pretty good job with this project. I _do_ realise it could have been better, but working on this application helped me to structure knowledge I gained by studying java in university (knowledge was limited to doing the job in a way that would be accepted, no more, no less). So yeah, I can spend an eternity cleaning this code, making it better and implementing all functionality. But sometimes you just need to say "stop" to yourself, because you need to realise that you've done enough already. I think it's the best project I've ever done. And I hope I will do much better in the future. Thanks for your attention!
