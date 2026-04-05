# Quick Start Guide - Personal Finance Companion App

## 📥 Installation & First Run

### Step 1: Build the App
```bash
cd expencetracker
./gradlew assembleDebug
```

### Step 2: Install on Device/Emulator
```bash
./gradlew installDebug
```

### Step 3: Launch the App
- Look for "Personal Finance" icon on your device
- Tap to open

---

## 🎮 Using the App

### Main Menu (First Screen)
You'll see 4 navigation cards:
1. **Dashboard** - View your financial overview
2. **Transactions** - Manage your income and expenses
3. **Goals** - Set and track savings goals
4. **Insights** - Analyze your spending patterns

---

## 📊 Dashboard Screen

**What You See:**
- Balance (Income - Expense)
- Total Income
- Total Expense
- Savings Status
- Pie chart showing income vs expense

**Actions:**
- Swipe down to refresh data
- Tap "Back" to return to menu

---

## 💳 Transaction Screen

### Adding a Transaction
1. Tap the "+" floating button
2. Enter the **Amount** (e.g., 500)
3. Select **Type**: Income or Expense
4. Enter **Category** (e.g., Food, Transport)
5. Tap "Pick Date" to select transaction date
6. Add optional **Notes**
7. Tap "Save"

### Editing a Transaction
1. Find the transaction in the list
2. Tap the **Edit** (pencil) icon
3. Modify fields as needed
4. Tap "Save"

### Deleting a Transaction
1. Find the transaction in the list
2. Tap the **Delete** (trash) icon
3. Confirm when asked

### Transaction List Display
- Shows amount with color coding
  - **Green**: Income transactions
  - **Red**: Expense transactions
- Displays category and date
- Shows type badge (INCOME/EXPENSE)

---

## 🎯 Goals Screen

### Creating a Quick Goal
1. Enter amount in the text field
2. Tap "Set Goal"
3. Progress bar updates as you save

### Creating a Detailed Goal
1. Tap the "+" floating button
2. Enter:
   - **Goal Title** (e.g., "Vacation Fund")
   - **Target Amount** (e.g., 50000)
   - **Category** (e.g., Travel)
3. Tap "Save"
4. Progress updates automatically

### Tracking Progress
- Progress bar shows how close you are to the goal
- Current balance displays at top
- Updates in real-time as transactions are added

---

## 📈 Insights Screen

You'll see several analytics cards:

1. **Top Spending Category**
   - Shows the category where you spend the most
   - Displays the amount spent

2. **Total Expense**
   - Sum of all expense transactions

3. **Average Transaction Size**
   - Average amount per transaction

4. **This Week Summary**
   - Income vs Expense comparison for the current week

5. **Bar Chart**
   - Visual comparison of weekly income and expense

---

## 🎨 Understanding the Colors

- **Green (Income)**: Money coming in
- **Red (Expense)**: Money going out
- **Blue (Primary)**: Main buttons and headers
- **Gray**: Secondary information and labels

---

## ⏰ Working with Dates

### Date Picker
When you tap "Pick Date":
- Calendar appears
- Select any date
- Confirmed date shows in the form

### Default Dates
- New transactions default to today
- App remembers last selected date for convenience

---

## 🔍 Sample Data

On first launch, the app loads 8 sample transactions:
- Mix of income and expense
- Various categories
- Spanning the last week
- Demonstrates all features

**This helps you:**
- See how the app works immediately
- Test analytics and charts
- Understand data entry

To start fresh: Settings > Apps > Personal Finance > Clear Data

---

## 💡 Tips & Tricks

### 1. Use Clear Categories
- Examples: Food, Transport, Utilities, Entertainment, Shopping
- Helps with analytics and insights

### 2. Check Dashboard Regularly
- Quick overview of financial health
- Pie chart shows spending breakdown

### 3. Review Weekly Trends
- Insights screen shows if spending is increasing/decreasing
- Good for budgeting decisions

### 4. Set Realistic Goals
- Goals should be achievable
- Short-term (monthly) and long-term (yearly) both useful

### 5. Add Notes for Large Transactions
- Helps remember what money was for
- Useful for categorization later

---

## 🆘 Troubleshooting

### App Crashes on Launch
**Solution:**
1. Clear app cache: Settings > Apps > Personal Finance > Storage > Clear Cache
2. Reinstall: Uninstall and rebuild

### Charts Not Showing
**Solution:**
1. Ensure you have transactions (add some if starting fresh)
2. Force refresh by navigating away and back
3. Restart the app

### No Transactions Displaying
**Solution:**
1. Check if data was cleared
2. Add new transaction manually
3. Sample data loads only on first launch

### Can't Edit/Delete Transaction
**Solution:**
- Ensure you tapped the correct button
- Edit button = pencil icon
- Delete button = trash icon

### Dates Not Saving
**Solution:**
- Confirm date in date picker
- Check if form has required fields filled
- Try entering date manually in database

---

## 📱 Supported Devices

- Android 7.0+ (API Level 24)
- All screen sizes from 4.5" to 6.7"
- Landscape and portrait orientations
- Works in light mode

---

## ⚙️ Settings & Data

### Where Data is Stored
- Local SQLite database on device
- Name: `finance_db`
- Location: App's internal storage

### Backing Up Data
**Current Version:** No cloud sync
**Options:**
1. Use device backup feature
2. Export data manually (future feature)
3. Take screenshots of insights

### Clearing Data
1. Go to Settings > Apps
2. Find "Personal Finance Companion"
3. Tap Storage > Clear Data
4. All transactions and goals will be deleted

---

## 🚀 Keyboard Shortcuts

- **Back Button**: Return to previous screen
- **Floating Action Button (+)**: Add new item
- **Tap Outside Dialog**: Close dialog (some screens)

---

## 📞 Common Questions

**Q: Can I restore deleted transactions?**
A: Not in current version. Tap delete carefully!

**Q: Can I export my data?**
A: Not yet. Future version will support CSV/PDF export.

**Q: Is my data secure?**
A: Data stays on your device. No cloud upload.

**Q: Can I use multiple accounts?**
A: Not in current version. Single user per installation.

**Q: Does it work offline?**
A: Yes, fully offline. No internet needed.

**Q: Can I change currency?**
A: Currently fixed to ₹ (Indian Rupees). Can be extended.

---

## 🎓 Learning the Interface

### First Time Setup (5 minutes)
1. Open app → See menu (30 seconds)
2. Tap Dashboard → See overview (1 minute)
3. Tap Transactions → Add one transaction (2 minutes)
4. See data update on Dashboard (1 minute)

### Full App Tour (15 minutes)
- Complete each screen once
- Add a few transactions
- Create a goal
- Check insights

### Productive Use (Ongoing)
- Add transactions daily
- Check dashboard weekly
- Review insights monthly
- Adjust goals as needed

---

## 🎯 Next Steps

1. **Add Your Data**
   - Replace sample transactions with your own
   - Or clear data and start fresh

2. **Set Goals**
   - Decide what you want to save for
   - Set realistic deadlines

3. **Monitor Trends**
   - Check insights every week
   - Identify spending patterns

4. **Adjust Habits**
   - Use insights to make better decisions
   - Track progress toward goals

---

## 📖 For More Information

- **Full Documentation**: See `README.md`
- **Feature List**: See `FEATURES.md`
- **Source Code**: Explore `app/src/main/java/`
- **Layouts**: Check `app/src/main/res/layout/`

---

**Happy tracking! 💰📊**

Version 1.0 | April 2026

