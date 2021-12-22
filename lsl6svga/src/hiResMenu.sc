;;; Sierra Script 1.0 - (do not remove this comment)
(script# 914)
(include sci.sh)
(use Main)
(use n076)
(use n078)
(use fileScr)
(use GraphMenuBar)
(use Print)
(use System)

(public
	hiResMenu 0
)

(instance tFile of MenuTitle
	(properties
		mainView 968
		winY 14
		winView 965
	)
	
	(method (init)
		(self add: iNew iOpen iSave iSaveAs iQuit)
		(super init: &rest)
	)
)

(instance tGame of MenuTitle
	(properties
		mainView 968
		mainLoop 1
		x 23
		winX 23
		winY 14
		winView 966
	)
	
	(method (init)
		(self
			add: iSaveOMatic iAutoSave iMusic iControls iMessage
		)
		(super init: &rest)
	)
)

(instance tHelp of MenuTitle
	(properties
		mainView 968
		mainLoop 2
		x 47
		winX 47
		winY 14
		winView 967
	)
	
	(method (init)
		(self
			add: iInterface iKeyboard iCustomerService iHints iYouMayEnjoy iAboutLarry
		)
		(super init: &rest)
	)
)

(instance iNew of MenuItem
	(properties
		y 1
		view 965
		loop 1
	)
	
	(method (select)
		(theGame restart:)
	)
)

(instance iOpen of MenuItem
	(properties
		y 11
		view 965
		loop 2
	)
	
	(method (select)
		(theGame restore:)
	)
)

(instance iSave of MenuItem
	(properties
		y 21
		view 965
		loop 3
	)
	
	(method (select)
		(theGame save: 0)
	)
)

(instance iSaveAs of MenuItem
	(properties
		y 31
		view 965
		loop 4
	)
	
	(method (select)
		(theGame save:)
	)
)

(instance iQuit of MenuItem
	(properties
		y 41
		view 965
		loop 5
	)
	
	(method (select)
		(theGame quitGame:)
	)
)

(instance iSaveOMatic of MenuItem
	(properties
		y 1
		view 966
		loop 1
	)
	
	(method (select)
		(proc78_1)
	)
)

(instance iAutoSave of MenuItem
	(properties
		y 11
		view 966
		loop 2
	)
	
	(method (select)
		(proc76_0)
	)
)

(instance iMusic of MenuItem
	(properties
		y 21
		view 966
		loop 3
	)
	
	(method (select)
		(cond 
			((theGame masterVolume:) (theGame masterVolume: 0) (= global194 0))
			((>= numVoices 1) (theGame masterVolume: 15) (= global194 13))
			(else (theGame masterVolume: 1) (= global194 1))
		)
	)
)

(instance iMessage of MenuItem
	(properties
		y 41
		view 966
		loop 5
	)
	
	(method (select)
		(if (Btst 105)
			(global208 show:)
			(Bclr 105)
		else
			(global208 hide:)
			(Bset 105)
		)
	)
)

(instance iControls of MenuItem
	(properties
		y 31
		view 966
		loop 4
	)
	
	(method (select &tmp theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: normalCursor)
		((ScriptID 94) init:)
		(theGame setCursor: theTheCursor)
	)
)

(instance iInterface of MenuItem
	(properties
		y 1
		view 9671
	)
	
	(method (select)
		(theGame setScript: interfaceHelpScr)
	)
)

(instance iKeyboard of MenuItem
	(properties
		y 11
		view 9671
		loop 1
	)
	
	(method (select)
		(Message 0 93 2 0 2 1 (global186 data?))
		(Print
			addTitle: (global186 data?)
			addText: 2 0 0 1 0 0 93
			init:
		)
	)
)

(instance iCustomerService of MenuItem
	(properties
		y 21
		view 9671
		loop 2
	)
	
	(method (select)
		(theGame setScript: custSupScript)
	)
)

(instance iHints of MenuItem
	(properties
		y 31
		view 9671
		loop 3
	)
	
	(method (select)
		(theGame setScript: hintScript)
	)
)

(instance iYouMayEnjoy of MenuItem
	(properties
		y 41
		view 9671
		loop 4
	)
	
	(method (select)
		(theGame setScript: youLikeScript)
	)
)

(instance iAboutLarry of MenuItem
	(properties
		y 51
		view 9671
		loop 5
	)
	
	(method (select)
		(theGame setScript: (ScriptID 73))
	)
)

(instance hiResMenu of GraphMenuBar
	(properties
		x 3
		y 2
	)
	
	(method (init)
		(self add: tFile tGame tHelp)
		(super init: &rest)
	)
)

(instance custSupScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message 0 93 3 0 2 1 (global186 data?))
				(Print
					addTitle: (global186 data?)
					addText: 3 0 0 1 0 0 93
					init: self
				)
			)
			(1
				(Print
					addTitle: (global186 data?)
					addText: 3 0 0 2 0 0 93
					init: self
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance hintScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message 0 93 5 0 2 1 (global186 data?))
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 1 0 0 93
					addButton: 100 5 0 1 1 0 35 93
					init: self
				)
			)
			(1
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 2 0 0 93
					init: self
				)
			)
			(2
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 3 0 0 93
					init: self
				)
			)
			(3
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 4 0 0 93
					init: self
				)
			)
			(4
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 5 0 0 93
					init: self
				)
			)
			(5
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 6 0 0 93
					init: self
				)
			)
			(6
				(Print
					addTitle: (global186 data?)
					addText: 5 0 0 7 0 0 93
					init: self
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance youLikeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message 0 93 6 0 2 1 (global186 data?))
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 1 0 0 93
					init: self
				)
			)
			(1
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 2 0 0 93
					init: self
				)
			)
			(2
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 3 0 0 93
					init: self
				)
			)
			(3
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 4 0 0 93
					init: self
				)
			)
			(4
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 5 0 0 93
					init: self
				)
			)
			(5
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 6 0 0 93
					init: self
				)
			)
			(6
				(Print
					addTitle: (global186 data?)
					addText: 6 0 0 7 0 0 93
					init: self
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance interfaceHelpScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Message 0 93 4 0 2 1 (global186 data?))
				(Print
					addTitle: (global186 data?)
					addText: 4 0 0 1 0 0 93
					init: self
				)
			)
			(1
				((ScriptID 75 0) init: doit: dispose:)
				(self dispose:)
			)
		)
	)
)
