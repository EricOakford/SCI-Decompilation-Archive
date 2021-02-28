;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_MAGIC)
(include game.sh) (include "21.shm")
(use Main)
(use GloryWindow)
(use Print)
(use IconBar)
(use Invent)
(use System)

(public
	glorySpells 0
)

(class SpellItem of InvItem
	(properties
		modNum NULL
		helpVerb 0
		owner 0
		script 0
		value 0
	)
	
	(method (select)
		(asm
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0112
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0112
			jmp      code_0110
code_0110:
			bt       code_011b
code_0112:
			pTos     value
			ldi      18
			lagi     egoStats
			lt?     
			bnt      code_0125
code_011b:
			pushi    #doVerb
			pushi    1
			pushi    4
			self     6
			jmp      code_0139
code_0125:
			pushi    #addText
			pushi    7
			pushi    0
			pushi    0
			pushi    2
			pushi    1
			pushi    0
			pushi    0
			pushi    21
			pushi    110
			pushi    0
			class    Print
			send     22
code_0139:
			ldi      0
			ret     
		)
	)
	
	(method (doVerb theVerb &tmp [nameBuf 20] [descBuf 80])
		(switch theVerb
			(V_LOOK
				(Message MsgGet GLORY_MAGIC noun NULL C_SPELL_NAMES 1 @nameBuf)
				(Message MsgGet GLORY_MAGIC NULL NULL C_SPELL_DESCRIPTION 1 @descBuf)
				(Print
					addTextF: @descBuf @nameBuf value [egoStats (+ OPEN (glorySpells indexOf: self))]
					init:
				)
			)
		)
	)
)

(instance spellsLook of IconItem
	(properties
		view 932
		loop 2
		cel 0
		cursor 941
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		modNum GLORY_MAGIC
		helpVerb V_HELP
	)
)

(instance spellsSelect of IconItem
	(properties
		view 932
		loop 0
		cel 0
		cursor 942
		noun N_SELECT
		modNum GLORY_MAGIC
		helpVerb V_HELP
	)
)

(instance ok of IconItem
	(properties
		view 932
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		modNum GLORY_MAGIC
		helpVerb V_HELP
	)
)

(instance spellsHelp of IconItem
	(properties
		view 932
		loop 1
		cel 0
		cursor 949
		message V_HELP
		noun N_HELP
		modNum GLORY_MAGIC
		helpVerb V_HELP
	)
	
	(method (show)
		(super show:)
		(DrawCel 932 7 0 (- (+ nsLeft (CelWide view loop cel)) 28) nsTop -1)
	)
)

(instance glorySpells of Inventory
	(properties
		normalHeading 9
		empty 27
	)
	
	(method (init &tmp i)
		(self
			add:
				openSpell
				detectMagicSpell
				triggerSpell
				dazzleSpell
				zapSpell
				calmSpell
				flameDartSpell
				fetchSpell
				forceBoltSpell
				levitateSpell
				reversalSpell
				jugglingLightsSpell
				summonStaffSpell
				lightningBallSpell
				healingSpell
				spellsLook
				spellsSelect
				ok
				spellsHelp
				dummyIcon
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #init
			window: spellWin
			helpIconItem: spellsHelp
			selectIcon: spellsSelect
			okButton: ok
			state: NOCLICKHELP
		)
		(= i 0)
		(while (< i NUM_SPELLS)
			(if [egoStats (+ OPEN i)]
				((self at: i) owner: ego)
			)
			(++ i)
		)
		(if
			(>=
				(cond 
					((< (if paladinStat (- [egoStats HONOR] paladinStat)) 0) 0)
					(paladinStat (- [egoStats HONOR] paladinStat))
				)
				25
			)
			(healingSpell owner: ego)
		)
	)
	
	(method (hide)
		(dummyIcon owner: 0)
		(super hide:)
	)
	
	(method (advance &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_02ae:
			lst      temp1
			pToa     size
			le?     
			bnt      code_0303
			pushi    64
			pushi    1
			lst      temp1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			add     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_02e0
			pushi    1
			pushi    #first
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_02e0:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_02f6
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_02f6
code_02f6:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_02ae
code_0303:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (retreat &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_0318:
			lst      temp1
			pToa     size
			le?     
			bnt      code_036e
			pushi    64
			pushi    1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			push    
			lat      temp1
			sub     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_034b
			pushi    1
			pushi    #last
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_034b:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_0361
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_0361
code_0361:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_0318
code_036e:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event ((user curEvent?) new:)) type?))
			(if (not (self isMemberOf: IconBar)) (event localize:))
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpVerb?))
						(= lastIcon thisIcon)
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(Print
							font: userFont
							width: 250
							addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
							modeless: TRUE
							init:
						)
						(SetPort oldPort)
					)
				)
				(modelessDialog
					(modelessDialog dispose:)
				)
				(else
					(= lastIcon 0)
				)
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: 942 TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(SetPort oldPort)
	)
	
	(method (drawInvWindow whom selection
						&tmp
						numOwned tallestInv widestInv
						numIcons tallestIcon iconBarWidth
						cWide cHigh node obj invH numCols
						invW iTop iLeft iBottom iRight numRows atX atY firstX i invWindow [buffer 70])
		(= numOwned
			(= tallestInv
				(= widestInv
					(= numIcons
						(= tallestIcon
							(= iconBarWidth 0)
						)
					)
				)
			)
		)
		(= node (self first:))
		(while node
			(if ((= obj (NodeValue node)) isKindOf: SpellItem)
				(if (obj ownedBy: whom)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(++ numOwned)
					(if
						(>
							(= cWide
								(CelWide (obj view?) (obj loop?) (obj cel?))
							)
							widestInv
						)
						(= widestInv cWide)
					)
					(if
						(>
							(= cHigh
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
							tallestInv
						)
						(= tallestInv cHigh)
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			else
				(++ numIcons)
				(= iconBarWidth
					(+ iconBarWidth (CelWide (obj view?) (obj loop?) (obj cel?)))
				)
				(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) tallestIcon)
					(= tallestIcon cHigh)
				)
			)
			(= node (self next: node))
		)
		(if (not numOwned)
			(return FALSE)
		)
		(if (<= numOwned 5)
			(= numRows 1)
			(= numCols numOwned)
		else
			(if
				(and
					(> (= numRows (Sqrt numOwned)) 1)
					(>= (* numRows numRows) numOwned)
				)
				(-- numRows)
			)
			(if (> numRows 3) (= numRows 3))
			(if (<= (* numRows (= numCols (/ numOwned numRows))) numOwned)
				(++ numCols)
			)
			(if (> numRows 1)
				(cond 
					((> numCols 8) (= numCols 8))
					((< numCols 5) (= numCols 5))
				)
			)
			(if (== (* numCols (- numRows 1)) numOwned) (-- numRows))
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 6 widestInv))))
		(= iTop
			(/ (- 190 (= invH (* numRows (+ 4 tallestInv)))) 2)
		)
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWindow (self window?))
			(invWindow
				top: iTop
				left: iLeft
				right: iRight
				bottom: iBottom
				open:
			)
		)
		(= i numCols)
		(if numOwned
			(= atY
				(+
					2
					(if (invWindow respondsTo: #yOffset)
						(invWindow yOffset?)
					else
						0
					)
				)
			)
			(= firstX
				(= atX
					(+
						8
						(if (invWindow respondsTo: #xOffset)
							(invWindow xOffset?)
						else
							0
						)
					)
				)
			)
			(= node (self first:))
			(while node
				(if
					(and
						(not
							(& ((= obj (NodeValue node)) signal?) DISABLED)
						)
						(obj isKindOf: SpellItem)
					)
					(if (not (& (obj signal?) FIXED_POSN))
						(obj
							nsLeft:
								(+
									atX
									(/
										(-
											widestInv
											(= cWide
												(CelWide (obj view?) (obj loop?) (obj cel?))
											)
										)
										2
									)
								)
							nsTop:
								(+
									atY
									(/
										(-
											tallestInv
											(= cHigh
												(CelHigh (obj view?) (obj loop?) (obj cel?))
											)
										)
										2
									)
								)
						)
						(obj
							nsRight: (+ (obj nsLeft?) cWide)
							nsBottom: (+ (obj nsTop?) cHigh)
						)
						(if (-- i)
							(= atX (+ atX widestInv MARGIN))
						else
							(= i numCols)
							(= atY (+ atY tallestInv 2))
							(= atX firstX)
						)
					else
						(= atX (obj nsLeft?))
						(= atY (obj nsTop?))
					)
					(obj show:)
					(if (== obj selection) (obj highlight:))
				)
				(= node (self next: node))
			)
		)
		(= atX
			(/
				(- (- (invWindow right?) (invWindow left?)) iconBarWidth)
				2
			)
		)
		(= invH (- (invWindow bottom?) (invWindow top?)))
		(= atY $7fff)
		(= node (self first:))
		(while node
			(if (not ((= obj (NodeValue node)) isKindOf: SpellItem))
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(= cHigh
					(CelHigh (obj view?) (obj loop?) (obj cel?))
				)
				(if (not (& (obj signal?) FIXED_POSN))
					(if (== atY 32767) (= atY (- invH cHigh)))
					(obj
						nsLeft: atX
						nsTop: atY
						nsBottom: invH
						nsRight: (+ atX cWide)
					)
				)
				(= atX (+ (obj nsLeft?) cWide))
				(= atY (obj nsTop?))
				(obj signal: (& (obj signal?) $fffb) show:)
			)
			(= node (self next: node))
		)
		(dummyIcon
			nsTop: 0
			nsLeft: 0
			nsRight: (window right?)
			nsBottom: (window bottom?)
			owner: whom
			signal: IMMEDIATE
		)
		(return TRUE)
	)
)

(instance spellWin of GloryWindow
	(properties
		yOffset 28
	)
	
	(method (open)
		(spellsLook nsLeft: (- (/ (- (self right?) (self left?)) 2) 60))
		(super open:)
	)
)

(instance dummyIcon of SpellItem
	(properties
		view 932
		cursor 942
	)
	
	(method (show)
		(= owner ego)
	)
	
	(method (select)
		(return FALSE)
	)
	
	(method (highlight)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(>= (theObjOrX x?) nsLeft)
					(>= (theObjOrX y?) nsTop)
					(<= (theObjOrX x?) nsRight)
				)
				(<= (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance openSpell of SpellItem
	(properties
		view 906
		cursor 938
		noun N_OPEN
		value 2
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				(cond 
					(
						(OneOf curRoomNum
							230 310 380 430 450 640
							650 700 810 820 853
						)
						((theIconBar at: ICON_CAST) message: V_OPEN cursor: 948)
						(theIconBar curIcon: (theIconBar at: ICON_CAST))
					)
					((> (ego view?) 5)
						(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
					)
					(else
						(ego setScript: (ScriptID CASTOPEN))
					)
				)
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance detectMagicSpell of SpellItem
	(properties
		view 906
		cel 1
		cursor 938
		noun N_DETECT
		value 2
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(cond 
						((OneOf curRoomNum 230 280 310 390 430 650 770 780 810 853)
							((= evt (Event new:)) type: mouseDown message: V_DETECT)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
						)
						(else
							(ego setScript: (ScriptID CASTAREA) 0 V_DETECT)
						)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance triggerSpell of SpellItem
	(properties
		view 906
		cel 2
		cursor 938
		noun N_TRIGGER
		value 3
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(cond 
						(
							(OneOf curRoomNum
								230 280 310 400 430 650
								700 850 851 852 853 854
							)
							((= evt (Event new:)) type: mouseDown message: V_TRIGGER)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
						)
						(else
							(ego setScript: (ScriptID CASTAREA) 0 V_TRIGGER)
						)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance dazzleSpell of SpellItem
	(properties
		view 906
		cel 3
		cursor 938
		noun N_DAZZLE
		value 3
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(cond 
						(
							(OneOf curRoomNum
								230 280 310 400 430 650
								700 851 852 853 854
							)
							((= evt (Event new:)) type: mouseDown message: V_DAZZLE)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
						)
						(else
							(ego setScript: (ScriptID CASTAREA) 0 V_DAZZLE)
						)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance zapSpell of SpellItem
	(properties
		view 906
		cel 4
		cursor 938
		noun N_ZAP
		value 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				(if (== curRoomNum 650)
					(curRoom doVerb: V_ZAP)
				else
					(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
					(if (or (ego has: iSword) (ego has: iFineDagger))
						(messager say: NULL NULL C_WEAPON_CHARGED 1 0 GLORY_MAGIC)
					else
						(messager say: NULL NULL C_NO_WEAPON 1 0 GLORY_MAGIC)
					)
				)
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance calmSpell of SpellItem
	(properties
		view 906
		cel 5
		cursor 938
		noun N_CALM
		value 4
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(cond 
						(
							(OneOf curRoomNum
								230 280 310 390 400 430 450 630
								650 700 820 850 851 852 853 854
							)
							((= evt (Event new:)) type: mouseDown message: V_CALM)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
						)
						(else
							(ego setScript: (ScriptID CASTAREA) 0 V_CALM)
						)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance flameDartSpell of SpellItem
	(properties
		view 906
		cel 6
		cursor 938
		noun N_FLAME
		value 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				((theIconBar at: ICON_CAST) message: V_FLAME cursor: 948)
				(theIconBar curIcon: (theIconBar at: ICON_CAST))
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance fetchSpell of SpellItem
	(properties
		view 906
		cel 7
		cursor 938
		noun N_FETCH
		value 5
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				((theIconBar at: ICON_CAST) message: V_FETCH cursor: 948)
				(theIconBar curIcon: (theIconBar at: ICON_CAST))
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance forceBoltSpell of SpellItem
	(properties
		view 906
		cel 8
		cursor 938
		noun N_FORCEBOLT
		value 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				((theIconBar at: ICON_CAST) message: V_FORCEBOLT cursor: 948)
				(theIconBar curIcon: (theIconBar at: ICON_CAST))
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance levitateSpell of SpellItem
	(properties
		view 906
		cel 9
		cursor 938
		noun N_LEVITATE
		value 7
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(cond 
						(
							(OneOf curRoomNum
								280 310 400 430 650 700
								720 740 851 852 853 854
							)
							((= evt (Event new:)) type: mouseDown message: V_LEVITATE)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_DONT_WASTE_SPELL 1 0 GLORY_MAGIC)
						)
						(((ScriptID curRoomNum) script?)
							(messager say: NULL NULL C_NO_LEVITATE_NOW 0 0 GLORY_MAGIC)
						)
						(else
							(messager say: N_CUE V_DOIT C_LEVITATE_FAIL 0 0 GLORY_MAGIC)
						)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance reversalSpell of SpellItem
	(properties
		view 906
		cel 10
		cursor 938
		noun N_REVERSAL
		value 8
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(if (OneOf curRoomNum 280 310 400 430 650 700 850 853)
						((= evt (Event new:)) type: mouseDown message: V_REVERSAL)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					else
						(Print addText: NULL NULL C_DONT_NEED_REVERSAL 1 0 0 GLORY_MAGIC init:)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance jugglingLightsSpell of SpellItem
	(properties
		view 906
		cel 11
		cursor 938
		noun N_JUGGLE
		value 8
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(if (OneOf curRoomNum 230 280 310 400 430 650 700 853)
						((= evt (Event new:)) type: mouseDown message: V_JUGGLE)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					else
						(ego setScript: (ScriptID CASTJUGGLE 0))
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance summonStaffSpell of SpellItem
	(properties
		view 906
		cel 12
		cursor 938
		noun N_SUMMON_STAFF
		value 5
	)
	
	(method (doVerb theVerb &tmp evt)
		(return
			(switch theVerb
				(V_DO
					(glorySpells hide:)
					(if (Btst fCanSummonStaff)
						(if (OneOf curRoomNum 280 310 400 430 650 700 850 853)
							((= evt (Event new:)) type: mouseDown message: V_STAFF)
							(if (not (mouseDownHandler handleEvent: evt))
								(regions handleEvent: evt)
							)
							(evt dispose:)
							(return TRUE)
						)
					else
						(messager say: N_CUE V_DOIT C_CANT_SUMMON_STAFF 0 0 GLORY_MAGIC)
					)
				)
				(V_LOOK
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance lightningBallSpell of SpellItem
	(properties
		view 906
		cel 13
		cursor 938
		noun N_LIGHTNING
		value 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(glorySpells hide:)
				((theIconBar at: ICON_CAST) message: V_LIGHTNING cursor: 948)
				(theIconBar curIcon: (theIconBar at: ICON_CAST))
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
		)
	)
)

(instance healingSpell of SpellItem
	(properties
		view 906
		cel 14
		cursor 938
		noun N_HEALING
		value 10
	)
	
	(method (select)
		(if (< [egoStats STAMINA] 10)
			(Print addText: N_CUE V_DOIT C_LOW_STAMINA 1 0 0 GLORY_MAGIC init:)
		else
			(self doVerb: V_DO)
		)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Print addText: noun theVerb NULL 1 0 0 GLORY_MAGIC init:)
			)
			(V_DO
				(glorySpells hide:)
				((theIconBar at: ICON_CAST) message: V_HEAL cursor: 948)
				(theIconBar curIcon: (theIconBar at: ICON_CAST))
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)
