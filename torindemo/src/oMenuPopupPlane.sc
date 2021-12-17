;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64000)
(include sci.sh)
(use Main)
(use GenDialg)
(use NewUser)
(use MenuItem)
(use Plane)
(use String)
(use Print)
(use Feature)

(public
	oMainMenu 0
	oMenuPopupPlane 1
	moHelp 2
)

(local
	local0
)
(instance oMenuPopupPlane of Plane
	(properties
		picture -2
		priority 600
	)
)

(instance oMenuPopupFeat of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsTop (= nsLeft 0))
		(= nsRight ((ScriptID 64000 0) findData:))
		(= nsBottom ((ScriptID 64000 0) doDouble:))
	)
	
	(method (handleEvent event)
		(if (self onMe: event) ((ScriptID 64000 0) show:))
		(return 0)
	)
)

(instance oKillMenuCheck of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (not (ScriptID 64000 0)) (return 0))
		(= temp1 0)
		(= temp0 (Clone event))
		(temp0 localize: (oMenuPopupFeat plane?))
		(if
			(and
				(user canControl:)
				(not (oMenuPopupFeat onMe: temp0))
				((ScriptID 64000 0) nExitDist:)
				(not ((ScriptID 64000 0) nScrollDir:))
			)
			((ScriptID 64000 0) hide:)
			(= temp1 1)
		)
		(temp0 dispose:)
		(return temp1)
	)
)

(instance oMainMenu of MenuHandler
	(properties
		sound -5534
		rExitTo 2
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler scriptId: oKillMenuCheck)
		(oMenuPopupPlane
			init:
				4
				4
				(+ ((ScriptID 64000 0) findData:) 4)
				(+ ((ScriptID 64000 0) doDouble:) 4)
		)
		(oMenuPopupFeat init: oMenuPopupPlane)
	)
	
	(method (oDragNotify)
		(scrollThumb addToEnd: moFile moGame moHelp)
	)
	
	(method (hide &tmp temp0)
		(super hide: &rest)
		(if ((ScriptID 64000 1) nScreenSizeX?)
			(= temp0 ((ScriptID 64000 1) priority?))
			((ScriptID 64000 1) setPri: 1)
			(UpdatePlane (ScriptID 64000 1))
			((ScriptID 64000 1) setPri: temp0)
			(UpdatePlane (ScriptID 64000 1))
		)
	)
)

(instance moFile of MenuItem
	(properties
		x 4
		y 4
		view -5534
	)
	
	(method (oDragNotify)
		(scrollThumb addToEnd: moNew moOpen moSave moExit)
	)
)

(instance moGame of MenuItem
	(properties
		view -5534
		loop 1
	)
	
	(method (oDragNotify)
		(scrollThumb
			addToEnd: moSpeed moScrolling moText moVolume
		)
	)
)

(instance moHelp of MenuItem
	(properties
		view -5534
		loop 2
	)
	
	(method (oDragNotify)
		(scrollThumb addToEnd: moGetHelp moSupport moAbout)
	)
)

(instance moNew of MenuItem
	(properties
		view -5534
		loop 3
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 1 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moOpen of MenuItem
	(properties
		view -5534
		loop 4
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 3 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moOldSave of MenuItem
	(properties
		view -5534
		loop 5
	)
	
	(method (oVerbs &tmp newStr newStr_2)
		(cond 
			((== global113 -1) (theGame save:))
			(
				(not
					(SaveGame
						0
						(theGame name?)
						global113
						(gNewStr data?)
						(KString 9 version)
					)
				)
				(= newStr (Str new:))
				(= newStr_2 (Str new:))
				(Message msgGET -542 10 0 0 1 (newStr data?))
				(Message msgGET -542 2 0 0 1 (newStr_2 data?))
				(Print
					font: 999
					fore: 235
					back: 234
					addText: newStr
					addButtonBM: -1 0 0 1 newStr_2 0 40
					init:
				)
				(newStr dispose:)
				(newStr_2 dispose:)
			)
			(else (Printf {Saved Game: %s} gNewStr))
		)
		(theGame addVerbHandler: gNewStr)
	)
)

(instance moSave of MenuItem
	(properties
		view -5534
		loop 5
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 2 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
		(if (= local0 (MakeMessageText 2 0 0 2 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moExit of MenuItem
	(properties
		view -5534
		loop 6
	)
	
	(method (oVerbs &tmp temp0 temp1 temp2)
		(= temp0 1)
		(= local0 (MakeMessageText 0 0 9 1 0))
		(= temp1 (MakeMessageText 0 0 8 1 0))
		(= temp2 (MakeMessageText 0 0 7 1 0))
		(if local0
			(= temp0 (proc64033_6 local0 temp1 temp2))
			(local0 dispose:)
			(= local0 0)
			(temp1 dispose:)
			(= temp1 0)
			(temp2 dispose:)
			(= temp2 0)
		)
		(if (not temp0) (return))
		(if (= local0 (MakeMessageText 4 0 4 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
		(curRoom newRoom: -1502)
	)
)

(instance moScrolling of MenuItem
	(properties
		view -5534
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(if global108 (self check:))
		(= global108 0)
		(= global205 50)
		(= global206 12)
		(= global109
			(- (- (thePlane findData:) 5) (* global205 2))
		)
		(= global110
			(- (- (thePlane doDouble:) 5) (* global206 2))
		)
		(self oStopNotify:)
	)
	
	(method (oVerbs)
		(if global108
			(= global108 0)
			(= global205 50)
			(= global206 12)
			(= global109
				(- (- (thePlane findData:) 5) (* global205 2))
			)
			(= global110
				(- (- (thePlane doDouble:) 5) (* global206 2))
			)
			(self oStopNotify:)
			(if (= local0 (MakeMessageText 0 0 3 1 0))
				(TextDialog local0 global288)
				(local0 dispose:)
				(= local0 0)
			)
		else
			(= global108 1)
			(= global205 240)
			(= global206 60)
			(self check:)
			(if (= local0 (MakeMessageText 0 0 4 1 0))
				(TextDialog local0 global288)
				(local0 dispose:)
				(= local0 0)
			)
		)
	)
)

(instance moVolume of MenuItem
	(properties
		view -5534
		loop 11
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 7 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moText of MenuItem
	(properties
		view -5534
		loop 9
	)
	
	(method (init)
		(super init: &rest)
		(if (& msgType $0001) (self check:))
	)
	
	(method (oVerbs)
		(if (not (& msgType $0001))
			(= msgType 3)
			(self check:)
			(if (= local0 (MakeMessageText 0 0 6 1 0))
				(TextDialog local0 global288)
				(local0 dispose:)
				(= local0 0)
			)
		else
			(= msgType 2)
			(self oStopNotify:)
			(if (= local0 (MakeMessageText 0 0 5 1 0))
				(TextDialog local0 global288)
				(local0 dispose:)
				(= local0 0)
			)
		)
	)
)

(instance moTitleBar of MenuItem
	(properties
		view -5534
		loop 10
	)
	
	(method (oVerbs)
		(Prints {Title Bar selected})
	)
)

(instance moSpeed of MenuItem
	(properties
		view -5534
		loop 7
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 5 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moGetHelp of MenuItem
	(properties
		view -5534
		loop 12
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 6 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moSupport of MenuItem
	(properties
		view -5534
		loop 13
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 9 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)

(instance moAbout of MenuItem
	(properties
		view -5534
		loop 14
	)
	
	(method (oVerbs)
		(if (= local0 (MakeMessageText 18 0 0 1 300))
			(TextDialog local0 global288)
			(local0 dispose:)
			(= local0 0)
		)
	)
)
