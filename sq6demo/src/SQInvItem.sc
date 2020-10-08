;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ6INV)
(include game.sh) (include "15.shm")
(use Main)
(use SQIconItem)
(use Plane)
(use String)
(use Print)
(use Tutorial)
(use Invent)
(use System)

(public
	SQInventory 0
)

(procedure (localproc_0adf param1 param2 param3 &tmp theObj theY theX)
	(= theX
		(+
			(/ (- (param1 nsRight?) (param1 x?)) 2)
			(param1 x?)
		)
	)
	(= theY param2)
	(while (>= (Abs (- theY param3)) 4)
		(if
			(= theObj
				(self
					firstTrue: #onMe (((user curEvent?) new:)
						x: theX
						y: theY
						yourself:
					)
				)
			)
			(return)
		)
		(if (< param2 param3)
			(= theY (+ theY 4))
		else
			(= theY (- theY 4))
		)
	)
)

(class SQInvItem of InvItem
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb V_LOOK)
				(if (Message MsgSize SQ6INV noun theVerb NULL 1)
					(messager say: noun V_LOOK NULL 0 0 SQ6INV)
				else
					(messager say: NULL V_LOOK NULL (RandTime 1 3) 0 15)
				)
			)
			((== theVerb V_DO)
				(if (Message MsgSize SQ6INV noun theVerb 0 1)
					(messager say: noun V_DO NULL 0 0 15)
				else
					(messager say: NULL V_DO NULL (RandTime 1 3) 0 15)
				)
			)
			((not (OneOf theVerb V_WALK V_DO V_TALK V_HELP))
				(if (Message MsgSize SQ6INV noun theVerb NULL 1)
					(messager say: noun theVerb NULL 0 0 SQ6INV)
				else
					(messager say: NULL V_ITEM NULL (RandTime 1 3) 0 SQ6INV)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (select param1 param2 &tmp [temp0 2])
		(if (or (super select: &rest) (and (> argc 1) param2))
			(if (not (theIconBar curInvIcon?))
				(theIconBar enableIcon: (theIconBar useIconItem?))
			)
			(theIconBar
				curIcon:
					((theIconBar useIconItem?)
						cursorView: cursorView
						cursorLoop: cursorLoop
						cursorCel: cursorCel
						yourself:
					)
				curInvIcon: self
				showInvItem: TRUE
			)
			(if (not (if (user controls?) (user input?)))
				(= oldCurIcon (theIconBar curIcon?))
			)
			(inventory
				curIcon: self
				selectedInvIcon: self
			)
		)
	)
	
	(method (highlight tOrF)
		(= cel (* 1 (if argc tOrF else 0)))
		(UpdateScreenItem self)
	)
	
	(method (cue)
		(inventory highlightedIcon: 0 eraseItems: drawInvItems:)
		(if (< (inventory numInv?) 9)
			(sliderIcon y: -15)
			(UpdateScreenItem sliderIcon)
			(inventory disableIcon: upIcon disableIcon: downIcon)
			(upIcon mask:)
			(downIcon mask:)
		else
			(sliderIcon y: 14)
			(UpdateScreenItem sliderIcon)
			(inventory enableIcon: upIcon enableIcon: downIcon)
			(upIcon show:)
			(downIcon show:)
		)
	)
)


(class SQInventory of Inventory
	(properties
		empty V_INVENTORY
		iconRight 160
		numRow 2
		numCol 4
		itemWide 41
		itemHigh 40
		selectedInvIcon 0
		numInv 0
	)
	
	(method (init)
		(= inventory self)
		(self
			add:
				Bjorn_Belt
				Bjorn_Chow
				Broken_Clap_Master
				Fixed_Clap_Master
				Duct_Tape
				Pin
				Pliers
				Wrapped_Pliers
				Dummy1_a
				Dummy2_a
				Dummy1_b
				Dummy2_b
				Dummy1_c
				Dummy2_c
				Dummy1_d
				Dummy2_d
				Dummy1_e
				Dummy2_e
				chooseIcon
				eyesIcon
				handsIcon
				sqHelpIcon
				playIcon
				upIcon
				downIcon
				sliderIcon
			selectIcon: chooseIcon
			helpIconItem: sqHelpIcon
			okButton: playIcon
			eachElementDo: #modNum SQ6INV
			state: NOCLICKHELP
		)
		(super init:)
		(invPlane init: 68 22 234 95)
		(plane setBitmap: 952 8 0 setRect: 61 16 258 119)
	)
	
	(method (doit &tmp thisIcon event eType eMsg eMod node newIcon buffer tut)
		(asm
			pushi    #newWith
			pushi    2
			pushi    100
			lofsa    {}
			push    
			class    String
			send     8
			sat      buffer
code_0b72:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      event
			send     4
			bnt      code_0b8e
			jmp      code_0b72
code_0b8e:
			ldi      0
			sat      node
code_0b92:
			pTos     state
			ldi      32
			and     
			bnt      code_1107
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      event
			pushi    #x
			pushi    0
			send     4
			sag      mouseX
			pushi    #y
			pushi    0
			lat      event
			send     4
			sag      mouseY
			pushi    #type
			pushi    0
			lat      event
			send     4
			sat      eType
			pushi    #message
			pushi    0
			lat      event
			send     4
			sat      eMsg
			pushi    #modifiers
			pushi    0
			lat      event
			send     4
			sat      eMod
			ldi      0
			sat      newIcon
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			pushi    0
			callk    FrameOut,  0
			pushi    #localize
			pushi    1
			pTos     plane
			lat      event
			send     6
			pushi    #initialized
			pushi    0
			lag      narrator
			send     4
			bnt      code_0c19
			pushi    #doit
			pushi    0
			lag      narrator
			send     4
			pushi    #handleEvent
			pushi    1
			lst      event
			lag      narrator
			send     6
			jmp      code_0b92
code_0c19:
			pushi    #initialized
			pushi    0
			pushi    2
			pushi    20
			pushi    1
			callk    ScriptID,  4
			send     4
			bnt      code_0c4c
			pushi    #doit
			pushi    0
			pushi    2
			pushi    20
			pushi    1
			callk    ScriptID,  4
			send     4
			pushi    #handleEvent
			pushi    1
			lst      event
			pushi    2
			pushi    20
			pushi    1
			callk    ScriptID,  4
			send     6
			jmp      code_0b92
code_0c4c:
			pToa     curIcon
			bnt      code_0cb3
			lat      eMod
			not     
			bnt      code_0cb3
			pTos     curIcon
			pToa     selectIcon
			ne?     
			bnt      code_0cb3
			lst      eType
			ldi      1
			eq?     
			bt       code_0c87
			lst      eType
			ldi      4
			eq?     
			bnt      code_0c7a
			lst      eMsg
			ldi      13
			eq?     
			bnt      code_0c7a
			ldi      1
			sat      newIcon
			bt       code_0c87
code_0c7a:
			lst      eType
			ldi      32
			eq?     
			bnt      code_0cb3
			ldi      1
			sat      newIcon
			bnt      code_0cb3
code_0c87:
			pTos     curIcon
			pToa     helpIconItem
			ne?     
			bt       code_0c9c
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			and     
			bnt      code_0cb3
code_0c9c:
			pushi    #type
			pushi    1
			pushi    16384
			pushi    49
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      event
			send     12
code_0cb3:
			pushi    1
			lst      event
			callk    MapKeyToDir,  2
			pushi    #type
			pushi    0
			lat      event
			send     4
			sat      eType
			pushi    #message
			pushi    0
			lat      event
			send     4
			sat      eMsg
			lag      cuees
			bnt      code_0cdb
			pushi    #eachElementDo
			pushi    1
			pushi    69
			send     6
code_0cdb:
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			sat      tut
			bnt      code_0cfe
			pushi    #isKindOf
			pushi    1
			class    Tutorial
			push    
			lat      tut
			send     6
			bnt      code_0cfe
			pushi    #doit
			pushi    0
			lat      tut
			send     4
code_0cfe:
			lst      eType
			ldi      1
			eq?     
			bnt      code_0d1c
			lat      eMod
			bnt      code_0d1c
			pushi    #advanceCurIcon
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lat      event
			send     6
			jmp      code_1040
code_0d1c:
			lst      eType
			ldi      0
			eq?     
			bnt      code_0d45
			pushi    #firstTrue
			pushi    2
			pushi    269
			lst      event
			self     8
			sat      thisIcon
			bnt      code_0d45
			push    
			pToa     highlightedIcon
			ne?     
			bnt      code_0d45
			pushi    #highlight
			pushi    1
			lst      thisIcon
			self     6
			jmp      code_1040
code_0d45:
			lst      eType
			ldi      1
			eq?     
			bt       code_0d62
			lst      eType
			ldi      4
			eq?     
			bnt      code_0d5a
			lst      eMsg
			ldi      13
			eq?     
			bt       code_0d62
code_0d5a:
			lst      eType
			ldi      32
			eq?     
			bnt      code_0e0b
code_0d62:
			pToa     highlightedIcon
			bnt      code_1040
			pushi    450
			pushi    #y
			push    
			lst      eType
			ldi      1
			eq?     
			push    
			self     8
			bnt      code_1040
			pTos     highlightedIcon
			pToa     okButton
			eq?     
			bnt      code_0d8e
			pushi    #claimed
			pushi    1
			pushi    1
			lat      event
			send     6
			jmp      code_1107
			jmp      code_1040
code_0d8e:
			pTos     highlightedIcon
			pToa     helpIconItem
			eq?     
			bnt      code_0dd8
			pToa     highlightedIcon
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
			pTos     state
			ldi      2048
			and     
			bnt      code_0dbd
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_1040
code_0dbd:
			pToa     helpIconItem
			bnt      code_1040
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_1040
code_0dd8:
			pToa     highlightedIcon
			aTop     curIcon
			pushi    4
			pTos     curIcon
			lofsa    upIcon
			push    
			lofsa    downIcon
			push    
			lofsa    sliderIcon
			push    
			calle    OneOf,  8
			not     
			bnt      code_1040
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
			jmp      code_1040
code_0e0b:
			lst      eType
			ldi      16
			and     
			bnt      code_0ec9
			lst      eMsg
			dup     
			ldi      3
			eq?     
			bnt      code_0e25
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0ec5
code_0e25:
			dup     
			ldi      7
			eq?     
			bnt      code_0e35
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0ec5
code_0e35:
			dup     
			ldi      1
			eq?     
			bnt      code_0e6f
			pToa     highlightedIcon
			bnt      code_0e65
			pushi    3
			push    
			pushi    #nsTop
			pushi    0
			send     4
			push    
			ldi      1
			sub     
			push    
			pushi    0
			call     localproc_0adf,  6
			sat      thisIcon
			bnt      code_0e65
			pushi    #highlight
			pushi    2
			lst      thisIcon
			pushi    1
			self     8
			jmp      code_0ec5
code_0e65:
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0ec5
code_0e6f:
			dup     
			ldi      5
			eq?     
			bnt      code_0eb1
			pToa     highlightedIcon
			bnt      code_0ea8
			pushi    3
			push    
			pushi    #nsBottom
			pushi    0
			send     4
			push    
			ldi      1
			add     
			push    
			pushi    #bottom
			pushi    0
			pToa     plane
			send     4
			push    
			call     localproc_0adf,  6
			sat      thisIcon
			bnt      code_0ea8
			pushi    #highlight
			pushi    2
			lst      thisIcon
			pushi    1
			self     8
			jmp      code_0ec5
code_0ea8:
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0ec5
code_0eb1:
			dup     
			ldi      0
			eq?     
			bnt      code_0ec5
			lst      eType
			ldi      4
			and     
			bnt      code_0ec5
			pushi    #advanceCurIcon
			pushi    0
			self     4
code_0ec5:
			toss    
			jmp      code_1040
code_0ec9:
			lst      eType
			ldi      4
			eq?     
			bnt      code_0edf
			lst      eMsg
			dup     
			ldi      27
			eq?     
			bnt      code_0edb
			jmp      code_1107
code_0edb:
			toss    
			jmp      code_1040
code_0edf:
			lst      eType
			ldi      16384
			and     
			bnt      code_1040
			pushi    #firstTrue
			pushi    2
			pushi    269
			lst      event
			self     8
			sat      thisIcon
			bnt      code_1040
			lst      eType
			ldi      8192
			and     
			bnt      code_0f70
			lat      thisIcon
			bnt      code_0f4d
			pushi    #noun
			pushi    0
			send     4
			bnt      code_0f4d
			pushi    7
			pushi    0
			pushi    #modNum
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    #noun
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    #helpVerb
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    0
			pushi    1
			pushi    #data
			pushi    0
			lat      buffer
			send     4
			push    
			callk    Message,  14
			bnt      code_0f4d
			pushi    1
			lst      buffer
			calle    Prints,  2
code_0f4d:
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      65519
			and     
			push    
			pToa     helpIconItem
			send     6
			pushi    #setCursor
			pushi    1
			lsg      normalCursor
			lag      theGame
			send     6
			jmp      code_1040
code_0f70:
			lst      thisIcon
			pToa     okButton
			eq?     
			bnt      code_0f86
			pushi    #claimed
			pushi    1
			pushi    1
			lat      event
			send     6
			jmp      code_1107
			jmp      code_1040
code_0f86:
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			lat      thisIcon
			send     6
			not     
			bnt      code_1007
			pushi    #select
			pushi    2
			lst      thisIcon
			lat      newIcon
			not     
			push    
			self     8
			bnt      code_1040
			lat      thisIcon
			aTop     curIcon
			pushi    4
			pTos     curIcon
			lofsa    upIcon
			push    
			lofsa    downIcon
			push    
			lofsa    sliderIcon
			push    
			calle    OneOf,  8
			not     
			bnt      code_0fd5
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
code_0fd5:
			lst      thisIcon
			pToa     helpIconItem
			eq?     
			bnt      code_1040
			pTos     state
			ldi      2048
			and     
			bnt      code_0fef
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_1040
code_0fef:
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_1040
code_1007:
			pToa     curIcon
			bnt      code_1040
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			pToa     curIcon
			send     6
			bnt      code_102e
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      thisIcon
			send     6
			jmp      code_1040
code_102e:
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			lat      event
			send     4
			push    
			lat      thisIcon
			send     6
code_1040:
			pushi    2
			pushi    #y
			pushi    0
			lat      event
			send     4
			lt?     
			bnt      code_1066
			pprev   
			ldi      85
			lt?     
			bnt      code_1066
			pushi    178
			pushi    #x
			pushi    0
			lat      event
			send     4
			lt?     
			bnt      code_1066
			pprev   
			ldi      195
			lt?     
			bt       code_1097
code_1066:
			pushi    #localize
			pushi    1
			pushi    #plane
			pushi    0
			lag      theIconBar
			send     4
			push    
			lat      event
			send     6
			bnt      code_10cc
			pushi    #y
			pushi    0
			lat      event
			send     4
			push    
			ldi      17
			ge?     
			bnt      code_10cc
			pushi    #x
			pushi    0
			lat      event
			send     4
			push    
			ldi      267
			lt?     
			bnt      code_10cc
code_1097:
			lsg      theCursor
			lag      normalCursor
			ne?     
			bnt      code_10f9
			pToa     highlightedIcon
			bnt      code_10af
			pushi    #highlight
			pushi    1
			pushi    0
			send     6
			ldi      0
			aTop     highlightedIcon
code_10af:
			pToa     curIcon
			sat      node
			pushi    #selectIcon
			pushi    0
			lag      inventory
			send     4
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			lsg      normalCursor
			lag      theGame
			send     6
			jmp      code_10f9
code_10cc:
			lat      node
			bnt      code_10f9
			lsg      theCursor
			pushi    #getCursor
			pushi    0
			send     4
			ne?     
			bnt      code_10f9
			lat      node
			aTop     curIcon
			ldi      0
			sat      node
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			pToa     curIcon
			send     4
			push    
			lag      theGame
			send     6
code_10f9:
			pushi    #handleEvent
			pushi    1
			lst      event
			lag      showTitle
			send     6
			jmp      code_0b92
code_1107:
			pushi    #dispose
			pushi    0
			lat      buffer
			send     4
			ret     
		)
	)
	
	(method (show &tmp obj node)
		(= state (| state IB_ACTIVE))
		(= curIcon chooseIcon)
		(theGame setCursor: (self getCursor:))
		(= curIndex 0)
		(= node (self first:))
		(while node
			(if
				(not
					((= obj (KList LNodeValue node)) isKindOf: InvItem)
				)
				(obj signal: (| (obj signal?) IB_ACTIVE))
			)
			(= node (self next: node))
		)
		(self drawInvItems:)
		(if (< numInv 9)
			(sliderIcon y: -15)
			(UpdateScreenItem sliderIcon)
			(inventory disableIcon: upIcon disableIcon: downIcon)
			(upIcon mask:)
			(downIcon mask:)
		else
			(sliderIcon y: 14)
			(UpdateScreenItem sliderIcon)
			(inventory enableIcon: upIcon enableIcon: downIcon)
			(upIcon show:)
			(downIcon show:)
		)
		(plane priority: (+ (GetHighPlanePri) 1))
		(UpdatePlane plane)
		(invPlane priority: (+ (GetHighPlanePri) 1))
		(UpdatePlane invPlane)
		(return 1)
	)
	
	(method (hide &tmp theCurs node obj)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(= state (& state (~ IB_ACTIVE)))
		)
		(= node (KList LFirstNode elements))
		(while node
			(= nextNode (KList LNextNode node))
			(= obj (KList LNodeValue node))
			(obj signal: (& (obj signal?) (~ IB_ACTIVE)))
			(if
				(and
					(obj isKindOf: InvItem)
					(not (& (obj signal?) DISABLED))
				)
				(obj signal: (| (obj signal?) DISABLED))
				(DeleteScreenItem obj)
			)
			(= node nextNode)
		)
		(invPlane priority: -1)
		(UpdatePlane invPlane)
		(plane priority: -1)
		(UpdatePlane plane)
		(if (Btst fInvActive)
			(if (or (Btst fWalkActive) (Btst fExitActive))
				(theIconBar enable: ICON_WALK)
			)
			(if (Btst fLookActive)
				(theIconBar enable: ICON_LOOK)
			)
			(if (Btst fDoActive)
				(theIconBar enable: ICON_DO)
			)
			(if (Btst fTalkActive)
				(theIconBar enable: ICON_TALK)
			)
			(if (Btst fHelpActive)
				(theIconBar enable: ICON_HELP)
			)
			(if (Btst fControlActive)
				(theIconBar enable: ICON_CONTROL)
			)
			(if
				(or
					(not selectedInvIcon)
					(not (curIcon isKindOf: InvItem))
				)
				(if (Btst fFixingClapmaster)
					(cond 
						((or (Btst fWalkActive) (Btst fExitActive))
							(theIconBar curIcon: (theIconBar at: ICON_WALK))
						)
						((Btst fLookActive)
							(theIconBar curIcon: (theIconBar at: ICON_LOOK))
						)
						((Btst fDoActive)
							(theIconBar curIcon: (theIconBar at: ICON_DO))
						)
						((Btst fTalkActive)
							(theIconBar curIcon: (theIconBar at: ICON_TALK))
						)
					)
					(Bclr fFixingClapmaster)
				else
					(theIconBar curIcon: gGSQIconbarCurIcon)
				)
			)
			(Bclr fWalkActive)
			(Bclr fLookActive)
			(Bclr fDoActive)
			(Bclr fTalkActive)
			(Bclr fHelpActive)
			(Bclr fControlActive)
			(Bclr fExitActive)
		)
		(theGame setCursor: ((theIconBar curIcon?) getCursor:))
	)
	
	(method (advanceCurIcon &tmp theIcon i newIcon)
		(if (& state DISABLED) (return))
		(= theIcon curIcon)
		(= i 0)
		(while
			(&
				((= theIcon (self at: (mod (+ (self indexOf: theIcon) 1) size))) signal?)
				(| IMMEDIATE DISABLED)
			)
			(if (> i (+ 1 size))
				(return)
			else
				(++ i)
			)
		)
		(= curIcon theIcon)
		(self highlight: curIcon)
		(theGame setCursor: (self getCursor:))
	)
	
	(method (drawIcons &tmp obj atX node cHigh cWide)
		(= atX
			(= node
				(= obj
					(= cHigh
						(= cWide 0)
					)
				)
			)
		)
		(= node (self first:))
		(while node
			(if
				(not
					((= obj (KList LNodeValue node)) isKindOf: InvItem)
				)
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(if
					(>
						(= cHigh
							(CelHigh (obj view?) (obj loop?) (obj cel?))
						)
						iconBottom
					)
					(= iconBottom cHigh)
				)
				(if (not (& (obj signal?) FIXED_POSN))
					(obj
						x: atX
						y: 0
						nsLeft: atX
						nsTop: 0
						nsRight: (+ atX cWide)
						nsBottom: cHigh
					)
				)
				(= atX (+ (obj x?) cWide))
				(UpdateScreenItem obj)
			)
			(= node (self next: node))
		)
	)
	
	(method (drawInvItems &tmp atX atY obj node)
		(= atX 0)
		(= atY -4)
		(= curIndex (= numInv 0))
		(= node (self first:))
		(while node
			(if
				(and
					((= obj (KList LNodeValue node)) isKindOf: InvItem)
					(== (obj owner?) owner)
				)
				(++ numInv)
				(obj
					x: atX
					y: atY
					nsLeft: atX
					nsTop: atY
					nsRight: (+ atX itemWide)
					nsBottom: (+ atY itemHigh)
					plane: invPlane
				)
				(if (& (obj signal?) DISABLED)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(AddScreenItem obj)
				else
					(UpdateScreenItem obj)
				)
				(if
				(> (= atX (+ atX itemWide colMargin)) iconRight)
					(= atX 0)
					(= atY (+ atY itemHigh rowMargin))
				)
			)
			(= node (self next: node))
		)
		(if node
			(= node (self next: node))
			(while node
				(if
					(and
						((= obj (KList LNodeValue node)) isKindOf: InvItem)
						(not (& (obj signal?) DISABLED))
					)
					(DeleteScreenItem obj)
					(obj signal: (| (obj signal?) DISABLED))
				)
				(= node (self next: node))
			)
		)
	)
	
	(method (carryingNothing)
		(messager say: NULL V_INVENTORY NULL 0 0 SQ6INV)
		(return FALSE)
	)
	
	(method (eraseItems &tmp node obj)
		(= node (self first:))
		(while node
			(if
			((= obj (KList LNodeValue node)) isKindOf: InvItem)
				(DeleteScreenItem obj)
				(obj signal: (| (obj signal?) DISABLED))
			)
			(= node (self next: node))
		)
	)
)

(instance chooseIcon of SQIconItem
	(properties
		noun N_SELECT
		x 7
		y 88
		signal (| RELVERIFY FIXED_POSN)
		mainView 952
		maskView 952
		maskCel 2
		cursorView ARROW_CURSOR
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
)

(instance eyesIcon of SQIconItem
	(properties
		noun N_LOOK
		x 44
		y 88
		signal (| RELVERIFY FIXED_POSN)
		message V_LOOK
		mainView 952
		mainLoop 1
		maskView 952
		maskLoop 1
		maskCel 2
		cursorView 953
		cursorLoop 1
		cursorCel 0
		helpVerb V_HELP
	)
)

(instance handsIcon of SQIconItem
	(properties
		noun N_USE
		x 82
		y 88
		signal (| RELVERIFY FIXED_POSN)
		message V_DO
		mainView 952
		mainLoop 2
		maskView 952
		maskLoop 2
		maskCel 2
		cursorView 953
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
)

(instance sqHelpIcon of SQIconItem
	(properties
		noun N_HELP
		x 119
		y 88
		signal (| RELVERIFY FIXED_POSN IMMEDIATE)
		type helpEvent
		mainView 952
		mainLoop 3
		maskView 952
		maskLoop 3
		maskCel 2
		cursorView 953
		cursorLoop 4
		cursorCel 0
		helpVerb V_HELP
	)
)

(instance playIcon of SQIconItem
	(properties
		noun N_OK
		x 156
		y 88
		signal (| RELVERIFY FIXED_POSN IMMEDIATE)
		mainView 952
		mainLoop 4
		maskView 952
		maskLoop 4
		maskCel 2
		cursorView ARROW_CURSOR
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
	
	(method (select)
		(= cel 0)
		(super select: &rest)
	)
)

(instance upIcon of SQIconItem
	(properties
		noun N_SCROLLUP
		x 182
		y 5
		signal (| RELVERIFY FIXED_POSN IMMEDIATE)
		mainView 952
		mainLoop 5
		maskView 952
		maskLoop 5
		maskCel 2
		cursorView ARROW_CURSOR
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
	
	(method (select &tmp i)
		(if (and (super select: &rest) (inventory curIndex?))
			(= i 0)
			(while (< i 40)
				(MovePlaneItems invPlane 0 1 0)
				(FrameOut)
				(++ i)
			)
			(inventory curIndex: (- (inventory curIndex?) 1))
			(sliderIcon
				y: (- (sliderIcon y?) (/ 55 (/ (- (inventory numInv?) 5) 4)))
			)
			(UpdateScreenItem sliderIcon)
		)
	)
)

(instance downIcon of SQIconItem
	(properties
		noun N_SCROLLDOWN
		x 182
		y 72
		signal (| RELVERIFY FIXED_POSN IMMEDIATE)
		mainView 952
		mainLoop 6
		maskView 952
		maskLoop 6
		maskCel 2
		cursorView ARROW_CURSOR
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
	
	(method (select &tmp i)
		(if
			(and
				(super select: &rest)
				(/
					(- (inventory numInv?) 1)
					(* (+ (inventory curIndex?) 2) 4)
				)
			)
			(= i 0)
			(while (< i 40)
				(MovePlaneItems invPlane 0 -1 0)
				(FrameOut)
				(++ i)
			)
			(inventory curIndex: (+ (inventory curIndex?) 1))
			(sliderIcon
				y: (+ (sliderIcon y?) (/ 55 (/ (- (inventory numInv?) 5) 4)))
			)
			(UpdateScreenItem sliderIcon)
		)
	)
)

(instance sliderIcon of SQIconItem
	(properties
		noun N_SLIDER
		x 183
		y 14
		signal (| RELVERIFY FIXED_POSN IMMEDIATE)
		mainView 952
		mainLoop 7
		maskView 952
		maskLoop 7
		maskCel 2
		cursorView ARROW_CURSOR
		cursorLoop 0
		cursorCel 0
		helpVerb V_HELP
	)
)

(instance Bjorn_Belt of SQInvItem
	(properties
		noun N_BJORNBELT
		signal IMMEDIATE
		message V_BJORNBELT
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
		name "Bjorn Belt"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== curRoomNum 360)
					(self select:)
					(inventory hide:)
					(FrameOut)
					(theGame setCursor: waitCursor TRUE)
					(curRoom notify:)
				else
					(messager say: noun NULL C_USEBELT 0 0 SQ6INV)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Bjorn_Chow of SQInvItem
	(properties
		noun N_BJORNCHOW
		signal IMMEDIATE
		message V_BJORNCHOW
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
		name "Bjorn Chow"
	)
)

(instance Broken_Clap_Master of SQInvItem
	(properties
		noun N_CLAPBROKEN
		signal IMMEDIATE
		message V_CLAPBROKEN
		mainView 90
		cursorView 90
		cursorLoop 0
		cursorCel 2
		name "Broken Clap Master"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_PIN
				(ego put: iBrokenClapmaster put: iPin get: iFixedClapmaster)
				(inventory select: (inventory selectIcon?))
				(theGame setCursor: normalCursor)
				(Bset fFixingClapmaster)
				(self cue:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(messager say: N_CLAPBROKEN V_PIN NULL 0 0 SQ6INV)
	)
)

(instance Duct_Tape of SQInvItem
	(properties
		noun N_DUCTTAPE
		signal IMMEDIATE
		message V_DUCTTAPE
		mainView 90
		mainLoop 3
		cursorView 90
		cursorLoop 3
		cursorCel 2
		name "Duct Tape"
	)
)

(instance Fixed_Clap_Master of SQInvItem
	(properties
		noun N_CLAPFIXED
		signal IMMEDIATE
		message V_CLAPFIXED
		mainView 90
		mainLoop 1
		cursorView 90
		cursorLoop 1
		cursorCel 2
		name "Fixed Clap Master"
	)
)

(instance Pin of SQInvItem
	(properties
		noun N_PIN
		signal IMMEDIATE
		message V_PIN
		mainView 90
		mainLoop 2
		cursorView 90
		cursorLoop 2
		cursorCel 2
	)
)

(instance Pliers of SQInvItem
	(properties
		noun N_PLIERS
		signal IMMEDIATE
		message V_PLIERS
		mainView 90
		mainLoop 4
		cursorView 90
		cursorLoop 4
		cursorCel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DUCTTAPE
				(messager say: noun theVerb NULL 0 self SQ6INV)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(ego put: iPliers put: iDuctTape get: iWrappedPliers)
		(inventory select: (inventory selectIcon?))
		(theGame setCursor: normalCursor)
		(Bset fFixingClapmaster)
		(super cue:)
	)
)

(instance Wrapped_Pliers of SQInvItem
	(properties
		noun N_TAPEDPLIERS
		signal IMMEDIATE
		message V_TAPEDPLIERS
		mainView 90
		mainLoop 5
		cursorView 90
		cursorLoop 5
		cursorCel 2
		name "Wrapped Pliers"
	)
)

(instance Dummy1_a of SQInvItem
	(properties
		noun 9
		signal IMMEDIATE
		message 18
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
	)
)

(instance Dummy2_a of SQInvItem
	(properties
		noun 12
		signal IMMEDIATE
		message 19
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
	)
)

(instance Dummy1_b of SQInvItem
	(properties
		noun 9
		signal IMMEDIATE
		message 18
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
	)
)

(instance Dummy2_b of SQInvItem
	(properties
		noun 12
		signal IMMEDIATE
		message 19
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
	)
)

(instance Dummy1_c of SQInvItem
	(properties
		noun 9
		signal IMMEDIATE
		message 18
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
	)
)

(instance Dummy2_c of SQInvItem
	(properties
		noun 12
		signal IMMEDIATE
		message 19
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
	)
)

(instance Dummy1_d of SQInvItem
	(properties
		noun 9
		signal IMMEDIATE
		message 18
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
	)
)

(instance Dummy2_d of SQInvItem
	(properties
		noun 12
		signal IMMEDIATE
		message 19
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
	)
)

(instance Dummy1_e of SQInvItem
	(properties
		noun 9
		signal IMMEDIATE
		message 18
		mainView 90
		mainLoop 7
		cursorView 90
		cursorLoop 7
		cursorCel 2
	)
)

(instance Dummy2_e of SQInvItem
	(properties
		noun 12
		signal IMMEDIATE
		message 19
		mainView 90
		mainLoop 6
		cursorView 90
		cursorLoop 6
		cursorCel 2
	)
)

(instance invPlane of Plane)
