# Auction
**The main idea of the project (please, bear with me):**
1. If you are a simple **_user_**
   - When you start the application for the first time, the menu which consists of `Log in`, `Sign up` and `Exit` commands appear.
        - If you are new to this app you can `Sign up` and then `Log in`
        - If you choose `Exit` then the program will stop
   - If you logged in, the following menu you will see consists of various commands that allow you to manage your items. If you don't have any, you can always add one. 
   - You can buy all items that are avaliable on this app. If you found something you like, you can _raise your card_ (basically offer your price). But remember, that the price you offer must be higher then the current price: every lot has its `auction step`. It's an amount of money by which you can raise the price. If the price you offered is higher, you bacome a **lot leader**. If nobody dares to change the current price within some time, item becomes yours. After the lot is closed and you are the winner, the contract between you and item's owner is signed. Congrats!!
   - If you don't have any money you can sell your items. For this, you need to `add item` first. Then create a lot. Remeber, set a reasonable `starting price` and `action step`. Nobody wants to buy junk for big money. And all you have to do is wait. 
2. Imagine you are an **_admin_**
   - You can `get users' information`. 
   - You can also `get information about any specific item`.
   - But you cannot delete any users or items. You are not that powerful.

## How to run
Actually, all you need to do is to start main method. This method will call method `startMenu` from class `Menu`. And this method will initialize imitated database, services and call methods from controllers in while loop. 

## Current state of things
I've implemented the following:
- User can log in with different roles
- User can `add item`, `view all items`, `view info of the specific item`, `remove all\specific item(s)`
- Admin can `view all users' information`, `get all items`, `view info of the specific item`
- If user stoped the app before exiting their account, they don't have to `log in` again.
- Application has exceptions for wrong input in case user misclicked. 
Mostly every project requirment from [this file](https://github.com/GritsH/Grits-JavaTraining-Auction/files/8192132/default.docx) was implemented.

## To be done:
- View layer
- Command pattern 
- Dao for files
