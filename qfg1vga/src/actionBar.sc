;;; Sierra Script 1.0 - (do not remove this comment)
(script# ACTIONBAR)
(include game.sh) (include "557.shm")
(use Main)
(use Sleep)
(use GloryWindow)
(use Procs)
(use IconBar)
(use GControl)

(public
	actionBar 0
)

(instance actionBar of GameControls
	(method (init)
		(self
			window: actWin
			add:
				iconNormal
				iconRun
				iconSneak
				iconRest
				iconSheet
				iconTime
				iconOk
				iconActionHelp
			eachElementDo: #init
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			curIcon: iconSheet
			okButton: iconOk
			helpIconItem: iconActionHelp
			state: (| OPENIFONME NOCLICKHELP)
		)
		(if (& disabledActions ACTION_WALK)
			(self disable: iconNormal)
		)
		(if (& disabledActions ACTION_RUN)
			(self disable: iconRun)
		)
		(if (& disabledActions ACTION_SNEAK)
			(self disable: iconSneak)
		)
		(if (& disabledActions ACTION_REST)
			(self disable: iconRest)
		)
	)
	
	(method (show)
		(super show:)
		(self dispose: release:)
		(if (Btst fCharSheetActive)
			(theGame setCursor: theCursor FALSE)
			(Bset fHideCursor)
			((ScriptID CHARSHEET) doit:)
		)
		(DisposeScript ACTIONBAR)
	)
	
	(method (hide)
		((theIconBar at: ICON_WALK) cursor:
			(switch egoGait
				(MOVE_WALK 940)
				(MOVE_RUN 937)
				(MOVE_SNEAK 947)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
		(super hide:)
	)
)

(instance iconNormal of IconItem
	(properties
		view 994
		loop 0
		cel 0
		nsLeft 10
		nsTop 4
		cursor 940
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun N_WALK
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(SetCurIcon ICON_WALK)
				(ChangeGait MOVE_WALK TRUE)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconRun of IconItem
	(properties
		view 994
		loop 1
		cel 0
		nsLeft 39
		nsTop 4
		cursor 937
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun N_RUN
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(SetCurIcon ICON_WALK)
				(ChangeGait MOVE_RUN TRUE)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconSneak of IconItem
	(properties
		view 994
		loop 2
		cel 0
		nsLeft 68
		nsTop 4
		cursor 947
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun N_SNEAK
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(SetCurIcon ICON_WALK)
				(if (!= egoGait MOVE_SNEAK)
					(if (TrySkill STEALTH 5 0)
						(ChangeGait MOVE_SNEAK TRUE)
					else
						(messager say: N_CANTDOTHAT NULL NULL 1 0 ACTIONBAR)
					)
				)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconRest of IconItem
	(properties
		view 994
		loop 3
		cel 0
		nsLeft 97
		nsTop 4
		cursor 943
		message V_SLEEP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun N_REST
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(SleepChoice)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconSheet of IconItem
	(properties
		view 994
		loop 5
		cel 0
		nsLeft 126
		nsTop 4
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun N_SHEET
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(cond 
					((not (HaveMem 2000))
						(messager say: N_CANTDOTHAT NULL NULL 2 0 ACTIONBAR)
					)
					((not isHandsOff)
						(Bset fCharSheetActive)
					)
				)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconTime of IconItem
	(properties
		view 994
		loop 6
		cel 0
		nsLeft 155
		nsTop 4
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun 8
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(ShowTime)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconOk of IconItem
	(properties
		view 994
		loop 4
		cel 0
		nsLeft 184
		nsTop 4
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 994
		maskLoop 9
		noun 3
		modNum ACTIONBAR
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconActionHelp of IconItem
	(properties
		view 994
		loop 8
		cel 0
		nsLeft 213
		nsTop 4
		cursor 949
		message V_HELP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_ACTIONHELP
		modNum ACTIONBAR
		helpVerb V_HELP
	)
)

(instance actWin of GloryWindow
	(method (open)
		(self top: 30 left: 37 bottom: 60 right: 280 priority: -1)
		(super open:)
	)
)
