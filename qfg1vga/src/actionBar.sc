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

(define ACTION_TOP		30)
(define ACTION_LEFT		37)
(define ACTION_BOTTOM	60)
(define ACTION_RIGHT	280)

(define ACTION_ICON_LEFT 10)

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
				(MOVE_WALK vWalkCursor)
				(MOVE_RUN vRunCursor)
				(MOVE_SNEAK vSneakCursor)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
		(super hide:)
	)
)

(instance iconNormal of IconItem
	(properties
		view vActionIcons
		loop 0
		cel 0
		nsLeft ACTION_ICON_LEFT
		nsTop MARGIN
		cursor vWalkCursor
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
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
		view vActionIcons
		loop 1
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 1))
		nsTop MARGIN
		cursor vRunCursor
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
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
		view vActionIcons
		loop 2
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 2))
		nsTop MARGIN
		cursor vSneakCursor
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
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
		view vActionIcons
		loop 3
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 3))
		nsTop MARGIN
		cursor vTalkCursor	;?
		message V_SLEEP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
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
		view vActionIcons
		loop 5
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 4))
		nsTop MARGIN
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
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
					((not (HaveMem SheetSize))
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
		view vActionIcons
		loop 6
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 5))
		nsTop MARGIN
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
		maskLoop 9
		noun N_TIME
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
		view vActionIcons
		loop 4
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 6))
		nsTop MARGIN
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView vActionIcons
		maskLoop 9
		noun N_OK
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
		view vActionIcons
		loop 8
		cel 0
		nsLeft (+ ACTION_ICON_LEFT (* 29 7))
		nsTop MARGIN
		cursor vHelpCursor
		message V_HELP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_ACTIONHELP
		modNum ACTIONBAR
		helpVerb V_HELP
	)
)

(instance actWin of GloryWindow
	(method (open)
		(self
			top: ACTION_TOP
			left: ACTION_LEFT
			bottom: ACTION_BOTTOM
			right: ACTION_RIGHT
			priority: -1
		)
		(super open:)
	)
)
