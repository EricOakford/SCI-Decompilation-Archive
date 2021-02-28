;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_ACTIONS)
(include game.sh) (include "20.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)

(public
	actionBar 0
)

(local
	charSheetCued
)
(instance actionBar of GameControls

	(method (init)
		(self add: iconRun iconSneak iconRest)
		(if (& disabledActions ACTION_RUN)
			(iconRun signal: (| (iconRun signal?) DISABLED))
		)
		(if (& disabledActions ACTION_SNEAK)
			(iconSneak signal: (| (iconSneak signal?) DISABLED))
		)
		(if (& disabledActions ACTION_REST)
			(iconRest signal: (| (iconRest signal?) DISABLED))
		)
		(self
			window: actWin
			add: iconSheet iconTime iconActionHelp iconEdge
			eachElementDo: #init
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			curIcon: iconSheet
			helpIconItem: iconActionHelp
			state: (| OPENIFONME NOCLICKHELP)
		)
	)
	
	(method (show)
		(super show:)
		(self dispose: release:)
		(if charSheetCued ((ScriptID CHARSHEET) doit:))
		(DisposeScript GLORY_ACTIONS)
	)
	
	(method (hide)
		(super hide:)
		(RestoreTheCursor)
	)
)

(instance iconRun of IconItem
	(properties
		view 931
		loop 0
		cel 0
		nsLeft 7
		nsTop 4
		cursor 940
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 931
		maskLoop 14
		noun N_RUN
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(ego changeGait: MOVE_RUN TRUE)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconSneak of IconItem
	(properties
		view 931
		loop 1
		cel 0
		nsLeft 36
		nsTop 4
		cursor 941
		message V_WALK
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 931
		maskLoop 14
		noun N_SNEAK
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(if (!= egoGait MOVE_SNEAK)
					(if (ego trySkill: STEALTH 5 0)
						(ego changeGait: MOVE_SNEAK TRUE)
					else
						(messager say: N_CANT_DO_THAT V_DOIT C_NO_STEALTH 0 0 GLORY_ACTIONS)
					)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconRest of IconItem
	(properties
		view 931
		loop 3
		cel 0
		nsLeft 65
		nsTop 4
		cursor 943
		message V_SLEEP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 931
		maskLoop 14
		noun N_REST
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				((ScriptID TIME 2) init:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconSheet of IconItem
	(properties
		view 931
		loop 5
		cel 0
		nsLeft 94
		nsTop 4
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 931
		maskLoop 14
		noun N_SHEET
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(actionBar hide:)
				(cond 
					((not (HaveMem 2100))
						(messager say: N_CANT_DO_THAT V_DOIT C_LOW_HEAP 0 0 GLORY_ACTIONS)
					)
					((not isHandsOff)
						(= charSheetCued TRUE)
					)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconTime of IconItem
	(properties
		view 931
		loop 6
		cel 0
		nsLeft 123
		nsTop 4
		cursor ARROW_CURSOR
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		maskView 931
		maskLoop 14
		noun N_TIME
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(if (super select: &rest)
			(actionBar hide:)
			((ScriptID TIME 3) init:)
		)
	)
)

(instance iconActionHelp of IconItem
	(properties
		view 931
		loop 8
		cel 0
		nsLeft 152
		nsTop 4
		cursor 949
		message V_HELP
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_HELP
		modNum GLORY_ACTIONS
		helpVerb V_HELP
	)
)

(instance iconEdge of IconItem
	(properties
		view 931
		loop 9
		cel 1
		nsLeft 181
		nsTop 4
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (select)
		(return FALSE)
	)
)

(instance actWin of GloryWindow
	
	(method (open)
		(self
			top: 30
			left: 61
			bottom: 67
			right: 254
			priority: -1
		)
		(super open:)
	)
)
