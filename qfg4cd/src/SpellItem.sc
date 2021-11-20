;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include sci.sh)
(use Main)
(use GloryIconItem)
(use String)
(use Print)
(use IconBar)
(use StopWalk)
(use Sound)
(use Invent)
(use Actor)
(use System)

(public
	glorySpells 0
)

(local
	newCast
	local1
)
(procedure (localproc_01a6 param1)
	(switch param1
		(5 20)
		(6 21)
		(7 22)
		(8 23)
		(9 24)
		(10 25)
		(11 26)
		(12 27)
		(13 28)
		(14 29)
		(15 30)
		(16 31)
		(17 32)
		(18 33)
		(20 41)
		(24 34)
		(26 35)
		(27 36)
		(31 38)
		(29 37)
		(30 39)
		(28 40)
	)
)

(class SpellItem of InvI
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 21
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 0
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0002
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $4000
		message 0
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 0
		lowlightColor 0
		helpVerb 9
		object 0
		selector 0
		owner 0
		script 0
		value 0
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(asm
			lsp      theVerb
			dup     
			ldi      1
			eq?     
			bnt      code_0334
			pushi    #new
			pushi    0
			class    Str
			send     4
			sat      temp0
			pushi    #new
			pushi    0
			class    Str
			send     4
			sat      temp1
			pushi    7
			pushi    0
			pushi    21
			pTos     noun
			pushi    0
			pushi    1
			pushi    1
			pushi    #data
			pushi    0
			lat      temp0
			send     4
			push    
			callk    Message,  14
			pushi    7
			pushi    0
			pushi    21
			pushi    0
			pushi    0
			pushi    3
			pushi    1
			pushi    #data
			pushi    0
			lat      temp1
			send     4
			push    
			callk    Message,  14
			pushi    418
			pushi    #scaleX
			pushi    #data
			pushi    0
			lat      temp1
			send     4
			push    
			pushi    #data
			pushi    0
			lat      temp0
			send     4
			push    
			pTos     value
			pushi    20
			pushi    #indexOf
			pushi    1
			pushSelf
			lofsa    glorySpells
			send     6
			add     
			lsgi     egoStats
			pushi    147
			pushi    0
			class    Print
			send     16
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			jmp      code_049c
code_0334:
			dup     
			ldi      4
			eq?     
			bnt      code_0492
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      5
			gt?     
			bnt      code_03ac
			pushi    47
			pushi    #view
			pushi    0
			lag      ego
			send     4
			le?     
			bnt      code_035b
			pprev   
			ldi      49
			le?     
code_035b:
			not     
			bnt      code_03ac
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      52
			ne?     
			bnt      code_03ac
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0388
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0388
code_0388:
			not     
			bnt      code_03ac
			lsg      curRoomNum
			ldi      670
			ne?     
			bnt      code_03ac
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    14
			pushi    1
			pushi    0
			pushi    21
			lag      messager
			send     16
			ldi      0
			ret     
			jmp      code_049c
code_03ac:
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			bnt      code_03d0
			pushi    #say
			pushi    6
			pushi    0
			pushi    0
			pushi    22
			pushi    1
			pushi    0
			pushi    21
			lag      messager
			send     16
			ldi      0
			ret     
			jmp      code_049c
code_03d0:
			pushi    8
			pushi    #indexOf
			pushi    1
			pushSelf
			lofsa    glorySpells
			send     6
			push    
			ldi      20
			add     
			push    
			pushi    26
			pushi    33
			pushi    28
			pushi    34
			pushi    20
			pushi    22
			pushi    27
			calle    OneOf,  16
			bnt      code_0450
			ldi      19
			lsgi     egoStats
			pushi    #indexOf
			pushi    1
			pushSelf
			lofsa    glorySpells
			send     6
			lagi     spellCost
			lt?     
			bnt      code_044b
			pushi    694
			pushi    2
			pushi    #indexOf
			pushi    1
			pushSelf
			lofsa    glorySpells
			send     6
			push    
			ldi      20
			add     
			push    
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_043f
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_043f
code_043f:
			push    
			lag      ego
			send     8
			ldi      0
			ret     
			jmp      code_049c
code_044b:
			ldi      1
			ret     
			jmp      code_049c
code_0450:
			pushi    694
			pushi    2
			pushi    #indexOf
			pushi    1
			pushSelf
			lofsa    glorySpells
			send     6
			push    
			ldi      20
			add     
			push    
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0480
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0480
code_0480:
			push    
			lag      ego
			send     8
			bnt      code_048d
			ldi      1
			ret     
			jmp      code_049c
code_048d:
			ldi      0
			ret     
			jmp      code_049c
code_0492:
			pushi    #doVerb
			pushi    1
			lsp      theVerb
			super    InvI,  6
code_049c:
			toss    
			ret     
		)
	)
	
	(method (select)
		(self doVerb: 4)
		(return 0)
	)
	
	(method (highlight)
	)
)

(instance glorySpells of QGInv
	(properties
		planeTop 29
		planeLeft 65
		numRow 3
		numCol 5
		rowMargin 1
		colMargin 8
		itemWide 23
		itemHigh 24
		iconMargin 5
		invLeft 26
	)
	
	(method (init &tmp temp0)
		(self
			helpIconItem: spellsHelp
			selectIcon: spellsSelect
			theSlider: spellsSlider
			okButton: 1
		)
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
				frostSpell
				ritualSpell
				invisibleSpell
				auraSpell
				protectionSpell
				resistanceSpell
				glideSpell
				healingSpell
				spellDummy1
				spellsLook
				spellsSelect
				spellsHelp
				ok
				spellDummy2
				spellsSlider
				spellsUpArrow
				spellsDownArrow
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #modNum 21
			state: 2048
		)
		(super init: &rest)
	)
	
	(method (hide)
		(thePlane deleteCast: local1)
		(super hide: &rest)
		(local1 dispose:)
		(= local1 0)
	)
	
	(method (showSelf param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(asm
			lap      param1
			aTop     owner
			ldi      0
			sat      temp6
code_0830:
			lst      temp6
			ldi      22
			lt?     
			bnt      code_0855
			pushi    20
			lat      temp6
			add     
			lagi     egoStats
			bnt      code_0851
			pushi    #owner
			pushi    1
			lsg      ego
			pushi    #at
			pushi    1
			lst      temp6
			self     6
			send     6
code_0851:
			+at      temp6
			jmp      code_0830
code_0855:
			lag      paladinStat
			bnt      code_0867
			push    
			ldi      14
			lagi     egoStats
			add     
			push    
			lag      paladinPoints
			sub     
			jmp      code_0869
code_0867:
			ldi      0
code_0869:
			push    
			ldi      25
			ge?     
			bnt      code_087b
			pushi    #owner
			pushi    1
			lsg      ego
			lofsa    healingSpell
			send     6
code_087b:
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0897
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0897
code_0897:
			not     
			bnt      code_08a5
			pushi    #owner
			pushi    1
			pushi    0
			lofsa    resistanceSpell
			send     6
code_08a5:
			pushi    3
			pushi    932
			pushi    8
			pushi    0
			callk    CelWide,  6
			sat      temp1
			pushi    3
			pushi    932
			pushi    8
			pushi    0
			callk    CelHigh,  6
			sat      temp0
			pushi    320
			lat      temp1
			sub     
			push    
			ldi      2
			div     
			sat      temp2
			pushi    153
			lat      temp0
			sub     
			push    
			ldi      2
			div     
			sat      temp4
			lst      temp2
			lat      temp1
			add     
			sat      temp3
			lst      temp4
			lat      temp0
			add     
			sat      temp5
			pushi    #back
			pushi    1
			pushi    108
			pToa     plane
			send     6
			pushi    #addCast
			pushi    1
			pushi    #new
			pushi    0
			class    Cast
			send     4
			sal      local1
			push    
			lag      thePlane
			send     6
			pushi    #view
			pushi    1
			pushi    932
			pushi    15
			pushi    1
			pushi    8
			pushi    16
			pushi    1
			pushi    0
			pushi    74
			pushi    1
			pushi    255
			pushi    332
			pushi    2
			lst      temp2
			lst      temp4
			pushi    147
			pushi    1
			lsl      local1
			pushi    #new
			pushi    0
			class    View
			send     4
			send     38
			pushi    #view
			pushi    1
			pushi    932
			pushi    15
			pushi    1
			pushi    9
			pushi    16
			pushi    1
			pushi    0
			pushi    74
			pushi    1
			pushi    255
			pushi    332
			pushi    2
			pushi    53
			pushi    143
			pushi    147
			pushi    1
			lsl      local1
			pushi    #new
			pushi    0
			class    View
			send     4
			send     38
			pushi    #getInvNum
			pushi    0
			self     4
			ldi      0
			aTop     curIndex
			aTop     currentRow
			pushi    #y
			pushi    1
			pushi    41
			pushi    #theSlider
			pushi    0
			lofsa    spellsSlider
			send     4
			send     6
			pushi    1
			pushi    #theSlider
			pushi    0
			lofsa    spellsSlider
			send     4
			push    
			callk    UpdateScreenItem,  2
			pushi    #pause
			pushi    0
			lag      sounds
			send     4
			lag      pMouse
			bnt      code_09b5
			pushi    #respondsTo
			pushi    1
			pushi    248
			send     6
			bnt      code_09b5
			pushi    #stop
			pushi    0
			lag      pMouse
			send     4
code_09b5:
			pToa     okButton
			not     
			bnt      code_09c9
			pushi    1
			pushi    #first
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			aTop     okButton
code_09c9:
			ldi      0
			aTop     curIcon
			lap      argc
			bnt      code_09d5
			lap      param1
			jmp      code_09d7
code_09d5:
			lag      ego
code_09d7:
			aTop     owner
			pushi    #ownedBy
			pushi    1
			pTos     owner
			self     6
			not     
			bnt      code_09ef
			pushi    #carryingNothing
			pushi    0
			self     4
			ldi      0
			ret     
code_09ef:
			pushi    #show
			pushi    0
			pushi    69
			pushi    0
			self     8
			lal      local1
			bnt      code_0a02
			pushi    #hide
			pushi    0
			self     4
code_0a02:
			ret     
		)
	)
	
	(method (ownedBy &tmp [temp0 2])
		(return 1)
	)
	
	(method (drawIcons)
		(plane addCast: (= newCast (Cast new:)))
		((View new:)
			view: 932
			loop: 10
			cel: 2
			posn: 0 26
			init: newCast
		)
		((View new:)
			view: 932
			loop: 10
			cel: 0
			posn: 3 28
			init: newCast
		)
		((View new:)
			view: 932
			loop: 10
			cel: 1
			posn: 170 28
			init: newCast
		)
		(spellsSlider
			theSlider:
				((View new:)
					x: 180
					y: 41
					view: 932
					loop: 12
					cel: 0
					init: newCast
					yourself:
				)
		)
		(super drawIcons: &rest)
	)
	
	(method (drawInvItems &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10)
		(asm
			ldi      0
			sat      temp4
			pToa     invLeft
			sat      temp0
			pTos     iconBottom
			ldi      15
			add     
			sat      temp1
			ldi      0
			sat      temp8
			sat      temp9
			pushi    #first
			pushi    0
			self     4
			sat      temp3
			ldi      0
			sat      temp7
code_05f6:
			lst      temp7
			pToa     curIndex
			lt?     
			bnt      code_064c
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			pushi    1
			lst      temp3
			callk    NodeValue,  2
			sat      temp2
			send     6
			bnt      code_063e
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_063e
			pushi    1
			lst      temp2
			callk    DeleteScreenItem,  2
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      4
			or      
			push    
			lat      temp2
			send     6
code_063e:
			+at      temp7
			pushi    #next
			pushi    1
			lst      temp3
			self     6
			sat      temp3
			jmp      code_05f6
code_064c:
			lat      temp3
			sat      temp3
code_0650:
			lat      temp3
			bnt      code_07c6
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			pushi    1
			lst      temp3
			callk    NodeValue,  2
			sat      temp2
			send     6
			bnt      code_07b9
			pushi    1
			pushi    #noun
			pushi    0
			lat      temp2
			send     4
			push    
			call     localproc_01a6,  2
			sat      temp10
			push    
			ldi      1
			or      
			bnt      code_06b7
			lat      temp10
			lagi     egoStats
			bnt      code_06b7
			lst      temp10
			ldi      39
			eq?     
			bnt      code_06b3
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_06ac
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_06ac
code_06ac:
			not     
			bnt      code_06b3
			ldi      0
			jmp      code_06b9
code_06b3:
			ldi      1
			jmp      code_06b9
code_06b7:
			ldi      0
code_06b9:
			bnt      code_07b9
			pushi    3
			pushi    #view
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp2
			send     4
			push    
			callk    CelHigh,  6
			sat      temp5
			push    
			lat      temp4
			gt?     
			bnt      code_06e9
			lat      temp5
			sat      temp4
code_06e9:
			pushi    1
			pushi    1
			lst      temp0
			pushi    2
			pushi    1
			lst      temp1
			pushi    19
			pushi    1
			lst      temp1
			pushi    18
			pushi    1
			lst      temp0
			pushi    20
			pushi    1
			lst      temp0
			pushi    3
			pushi    #view
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp2
			send     4
			push    
			callk    CelWide,  6
			add     
			push    
			pushi    21
			pushi    1
			lst      temp1
			lat      temp5
			add     
			push    
			lat      temp2
			send     36
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      4
			and     
			bnt      code_075d
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      65531
			and     
			push    
			lat      temp2
			send     6
			pushi    1
			lst      temp2
			callk    AddScreenItem,  2
			jmp      code_0764
code_075d:
			pushi    1
			lst      temp2
			callk    UpdateScreenItem,  2
code_0764:
			lst      temp0
			pushi    3
			pushi    #view
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp2
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp2
			send     4
			push    
			callk    CelWide,  6
			push    
			pToa     colMargin
			add     
			add     
			sat      temp0
			push    
			pToa     iconRight
			gt?     
			bt       code_079c
			+at      temp9
			push    
			pToa     numCol
			ge?     
			bnt      code_07b9
code_079c:
			ldi      0
			sat      temp9
			pToa     invLeft
			sat      temp0
			lst      temp1
			pToa     itemHigh
			add     
			push    
			pToa     rowMargin
			add     
			sat      temp1
			+at      temp8
			push    
			pToa     numRow
			ge?     
			bnt      code_07b9
			jmp      code_07c6
code_07b9:
			pushi    #next
			pushi    1
			lst      temp3
			self     6
			sat      temp3
			jmp      code_0650
code_07c6:
			lat      temp3
			bnt      code_0825
			pushi    #next
			pushi    1
			push    
			self     6
			sat      temp3
code_07d4:
			lat      temp3
			bnt      code_0825
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			pushi    1
			lst      temp3
			callk    NodeValue,  2
			sat      temp2
			send     6
			bnt      code_0819
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_0819
			pushi    1
			lst      temp2
			callk    DeleteScreenItem,  2
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			lat      temp2
			send     4
			push    
			ldi      4
			or      
			push    
			lat      temp2
			send     6
code_0819:
			pushi    #next
			pushi    1
			lst      temp3
			self     6
			sat      temp3
			jmp      code_07d4
code_0825:
			ret     
		)
	)
	
	(method (getInvNum &tmp qGInvFirst temp1 temp2)
		(= temp1 0)
		(= qGInvFirst (self first:))
		(while qGInvFirst
			(if
				(and
					((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
					(== (temp2 owner?) ego)
				)
				(++ temp1)
			)
			(= qGInvFirst (self next: qGInvFirst))
		)
		(if (= totalRow (/ temp1 numCol))
			(= interval (/ 59 totalRow))
		else
			(= interval 60)
		)
	)
)

(instance spellsSlider of SliderIcon
	(properties
		noun 33
		nsLeft 180
		nsTop 39
		nsRight 191
		nsBottom 98
		x 180
		y 39
		signal $0083
		message 4
		mainView 932
		mainLoop 15
		helpVerb 9
	)
	
	(method (select &tmp temp0 temp1 [temp2 4] temp6)
		(while
			(not
				(OneOf
					((= temp0 ((user curEvent?) new:)) type?)
					2
					8
					64
				)
			)
			(temp0 localize: (glorySpells plane?))
			(cond 
				((< (temp0 y?) 41) (theSlider y: 41))
				((> (temp0 y?) 92) (theSlider y: 92))
				(else (theSlider y: (temp0 y?)))
			)
			(UpdateScreenItem theSlider)
			(FrameOut)
		)
		(temp0 localize: (glorySpells plane?))
		(cond 
			(
				(or
					(not
						(= temp1
							(/ (+ (- (temp0 y?) 41) 8) (glorySpells interval?))
						)
					)
					(< (temp0 y?) 41)
				)
				(self updateSlider: 0 0)
				(while (> (glorySpells currentRow?) 0)
					(glorySpells
						currentRow: (- (glorySpells currentRow?) 1)
					)
					(glorySpells setCurIndex: -5)
				)
			)
			(
				(or
					(== temp1 (glorySpells totalRow?))
					(> (temp0 y?) 92)
				)
				(self updateSlider: 0 1)
				(while
				(< (glorySpells currentRow?) (glorySpells totalRow?))
					(glorySpells
						currentRow: (+ (glorySpells currentRow?) 1)
					)
					(glorySpells setCurIndex: 5)
				)
			)
			(else
				(theSlider y: (+ 41 (* temp1 (glorySpells interval?))))
				(UpdateScreenItem theSlider)
				(cond 
					(
					(> (= temp6 (- temp1 (glorySpells currentRow?))) 0)
						(while temp6
							(glorySpells
								currentRow: (+ (glorySpells currentRow?) 1)
							)
							(glorySpells setCurIndex: 5)
							(-- temp6)
						)
					)
					((< temp6 0)
						(while temp6
							(glorySpells
								currentRow: (- (glorySpells currentRow?) 1)
							)
							(glorySpells setCurIndex: -5)
							(++ temp6)
						)
					)
				)
			)
		)
		(glorySpells drawInvItems:)
		(return 0)
	)
	
	(method (updateSlider param1 param2 &tmp temp0)
		(if (> argc 1)
			(if param2 (theSlider y: 92) else (theSlider y: 41))
		else
			(theSlider y: (+ (theSlider y?) param1))
		)
		(UpdateScreenItem theSlider)
	)
)

(instance spellsUpArrow of GloryIconItem
	(properties
		noun 34
		nsLeft 180
		nsTop 30
		nsRight 191
		nsBottom 38
		x 180
		y 30
		signal $0083
		message 4
		mainView 932
		mainLoop 14
		helpVerb 9
	)
	
	(method (select)
		(if (>= (- (glorySpells currentRow?) 1) 0)
			(glorySpells
				currentRow: (- (glorySpells currentRow?) 1)
			)
			(if (== (glorySpells currentRow?) 0)
				(spellsSlider updateSlider: 0 0)
			else
				(spellsSlider
					updateSlider: (- 0 (glorySpells interval?))
				)
			)
			(glorySpells setCurIndex: -5 drawInvItems:)
		)
		(return 0)
	)
)

(instance spellsDownArrow of GloryIconItem
	(properties
		noun 35
		nsLeft 180
		nsTop 100
		nsRight 191
		nsBottom 108
		x 180
		y 100
		signal $0083
		message 4
		mainView 932
		mainLoop 13
		helpVerb 9
	)
	
	(method (select)
		(if
			(<=
				(+ (glorySpells currentRow?) 1)
				(glorySpells totalRow?)
			)
			(glorySpells
				currentRow: (+ (glorySpells currentRow?) 1)
			)
			(if
			(== (glorySpells currentRow?) (glorySpells totalRow?))
				(spellsSlider updateSlider: 0 1)
			else
				(spellsSlider updateSlider: (glorySpells interval?))
			)
			(glorySpells setCurIndex: 5 drawInvItems:)
		)
		(return 0)
	)
)

(instance spellDummy1 of GloryIconItem
	(properties
		signal $0083
		mainView 932
		mainLoop 7
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance spellDummy2 of GloryIconItem
	(properties
		signal $0043
		mainView 932
		mainLoop 7
		mainCel 1
	)
	
	(method (onMe)
		(return 0)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance spellsLook of IconI
	(properties
		noun 1
		modNum 21
		nsTop 10
		message 1
		mainView 932
		mainLoop 2
		cursorView 941
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance spellsSelect of IconI
	(properties
		noun 4
		modNum 21
		nsTop 10
		mainView 932
		cursorView 942
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance ok of IconI
	(properties
		noun 2
		modNum 21
		nsTop 10
		mainView 932
		mainLoop 3
		cursorView 942
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
	
	(method (init)
		(self setCursor: 942 0 0)
		(super init: &rest)
	)
	
	(method (select)
		(glorySpells hide:)
		(return 1)
	)
)

(instance spellsHelp of IconI
	(properties
		noun 3
		modNum 21
		nsTop 10
		signal $0003
		message 9
		mainView 932
		mainLoop 1
		cursorView 949
		cursorLoop 0
		cursorCel 0
		helpVerb 9
	)
)

(instance openSpell of SpellItem
	(properties
		noun 5
		mainView 906
		value 2
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(glorySpells hide:)
				(cond 
					((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 80))
					((super doVerb: 4)
						((theIconBar at: 5)
							message: 80
							cursorView: 948
							cursorLoop: 0
							cursorCel: 0
						)
						(theIconBar curIcon: (theIconBar at: 5))
					)
					(else (messager say: 0 0 23 1 0 21))
				)
			)
			(1 (super doVerb: theVerb))
		)
	)
)

(instance detectMagicSpell of SpellItem
	(properties
		noun 6
		mainView 906
		mainCel 1
		value 2
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(glorySpells hide:)
				(cond 
					((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 81))
					((super doVerb: 4) (curRoom setScript: (ScriptID 12) 0 81))
				)
			)
			(1 (super doVerb: theVerb))
		)
	)
)

(instance triggerSpell of SpellItem
	(properties
		noun 7
		mainView 906
		mainCel 2
		value 3
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(glorySpells hide:)
				(cond 
					((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 82))
					((super doVerb: 4)
						((theIconBar at: 5)
							message: 82
							cursorView: 948
							cursorLoop: 0
							cursorCel: 0
						)
						(theIconBar curIcon: (theIconBar at: 5))
					)
					(else (messager say: 0 0 24 0 0 21))
				)
			)
			(1 (super doVerb: theVerb))
		)
	)
)

(instance dazzleSpell of SpellItem
	(properties
		noun 8
		mainView 906
		mainCel 3
		value 3
	)
	
	(method (doVerb theVerb &tmp newEvent)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 83))
						((super doVerb: 4)
							(cond 
								(
									(OneOf
										curRoomNum
										551
										552
										553
										554
										555
										556
										557
										558
										559
										560
										561
										562
										563
										564
										565
										566
										567
										568
										569
										570
										571
										572
										573
										574
										575
										576
										577
										578
										579
										580
										581
										582
										583
										584
										585
										586
										587
										588
										589
										590
										591
										592
										593
										270
										340
										460
										520
										545
										600
										625
										650
										670
										710
										730
										750
										770
									)
									((= newEvent (Event new:)) type: 1 message: 83)
									(if (not (mouseDownHandler handleEvent: newEvent))
										(regions handleEvent: newEvent)
									)
									(newEvent dispose:)
									(return 1)
								)
								((> (ego view?) 5) (messager say: 0 0 14 1 0 21))
								(else (ego setScript: (ScriptID 12) 0 83))
							)
						)
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance zapSpell of SpellItem
	(properties
		noun 9
		mainView 906
		mainCel 4
		value 3
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 84))
						((super doVerb: 4)
							(cond 
								((== curRoomNum 650) (curRoom doVerb: 84))
								((or (ego has: 19) (ego has: 5))
									(soundFX number: 932 play:)
									(messager say: 0 0 8 1 0 21)
									(= zapPower (+ 5 (/ [egoStats 24] 10)))
									((ScriptID 0 21) doit:)
								)
								(else (messager say: 0 0 9 1 0 21))
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance calmSpell of SpellItem
	(properties
		noun 10
		mainView 906
		mainCel 5
		value 4
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 85))
						((super doVerb: 4) (curRoom setScript: (ScriptID 12) 0 85))
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance flameDartSpell of SpellItem
	(properties
		noun 11
		mainView 906
		mainCel 6
		value 5
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 86))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 86
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance fetchSpell of SpellItem
	(properties
		noun 12
		mainView 906
		mainCel 7
		value 5
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 87))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 87
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance forceBoltSpell of SpellItem
	(properties
		noun 13
		mainView 906
		mainCel 8
		value 6
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 88))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 88
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance levitateSpell of SpellItem
	(properties
		noun 14
		mainView 906
		mainCel 9
		value 7
	)
	
	(method (doVerb theVerb &tmp newEvent newStr)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 89))
						(
							(and
								(== curRoomNum 710)
								(== (ego script?) (ScriptID 710 1))
								(== ((ego script?) state?) 3)
							)
							((ego script?) register: 1)
							((ego script?) cue:)
						)
						((super doVerb: 4)
							(if
								(OneOf
									curRoomNum
									551
									552
									553
									554
									555
									556
									557
									558
									559
									560
									561
									562
									563
									564
									565
									566
									567
									568
									569
									570
									571
									572
									573
									574
									575
									576
									577
									578
									579
									580
									581
									582
									583
									584
									585
									586
									587
									588
									589
									590
									591
									592
									593
									250
									260
									270
									280
									290
									330
									340
									600
									710
									720
									800
									740
									750
									730
								)
								((= newEvent (Event new:)) type: 1 message: 89)
								(if (not (mouseDownHandler handleEvent: newEvent))
									(regions handleEvent: newEvent)
								)
								(newEvent dispose:)
								(return 1)
							else
								(= newStr (Str new:))
								(cond 
									((> (ego view?) 5)
										(Message msgGET 21 0 0 14 1 (newStr data?))
										(Print addText: (newStr data?) init:)
									)
									(((ScriptID curRoomNum) script?)
										(Message msgGET 21 0 0 15 1 (newStr data?))
										(Print addText: (newStr data?) init:)
									)
									(else
										(Message msgGET 21 21 6 21 1 (newStr data?))
										(Print addText: (newStr data?) init:)
									)
								)
								(newStr dispose:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance reversalSpell of SpellItem
	(properties
		noun 15
		mainView 906
		mainCel 10
		value 8
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 90))
						((super doVerb: 4)
							(if (OneOf curRoomNum 270 340 460 750)
								(curRoom doVerb: 90)
							else
								(Bset 8)
								(= reversalTimer (/ [egoStats 30] 2))
								(messager say: 0 0 27 0 0 21)
								((ScriptID 0 21) doit:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance jugglingLightsSpell of SpellItem
	(properties
		noun 16
		mainView 906
		mainCel 11
		value 8
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 91))
						((super doVerb: 4)
							(cond 
								(
									(and
										(> (ego view?) 17)
										(< (ego view?) 21)
										(< (ego view?) 21)
									)
									(messager say: 21 6 33 0 0 21)
								)
								(
									(OneOf
										curRoomNum
										270
										340
										390
										520
										530
										535
										541
										542
										543
										545
										600
										632
										630
										670
										680
										750
										770
										800
									)
									(curRoom doVerb: 91)
									(return 1)
								)
								(else (curRoom setScript: (ScriptID 62)))
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance summonStaffSpell of SpellItem
	(properties
		noun 17
		mainView 906
		mainCel 12
		value 5
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 92))
						((or (Btst 450) debugging)
							(if (super doVerb: 4)
								(cond 
									(
										(and
											(> (ego view?) 17)
											(< (ego view?) 21)
											(< (ego view?) 21)
										)
										(ego
											normalize:
												(cond 
													((== (ego view?) 18) (+ (ego loop?) 4))
													((< (ego loop?) 4) (+ (ego loop?) 2))
													(else (ego loop?))
												)
										)
									)
									((OneOf curRoomNum 270 340 460 670 740 750 730) (curRoom doVerb: 92))
									(else (curRoom setScript: (ScriptID 46)))
								)
							else
								(return 1)
							)
						)
						(else (messager say: 17 92 31 0 0 21))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance lightningBallSpell of SpellItem
	(properties
		noun 18
		mainView 906
		mainCel 13
		value 10
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 93))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 93
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance healingSpell of SpellItem
	(properties
		noun 20
		mainView 906
		mainCel 14
		value 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Print addText: noun theVerb 0 1 0 0 21 init:)
			)
			(4
				(glorySpells hide:)
				(cond 
					((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 102))
					((< [egoStats 18] 10) (messager say: 21 6 20 0 0 21))
					(else
						((theIconBar at: 5)
							message: 102
							cursorView: 948
							cursorLoop: 0
							cursorCel: 0
						)
						(theIconBar curIcon: (theIconBar at: 5))
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance frostSpell of SpellItem
	(properties
		noun 24
		mainView 906
		mainLoop 1
		mainCel 3
		value 15
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 79))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 79
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (select)
		(self doVerb: 4)
		(return 0)
	)
)

(instance ritualSpell of SpellItem
	(properties
		noun 26
		mainView 906
		mainLoop 1
		mainCel 1
		value 20
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 94))
						((super doVerb: 4)
							((theIconBar at: 5)
								message: 94
								cursorView: 948
								cursorLoop: 0
								cursorCel: 0
							)
							(theIconBar curIcon: (theIconBar at: 5))
						)
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (select)
		(self doVerb: 4)
		(return 0)
	)
)

(instance invisibleSpell of SpellItem
	(properties
		noun 27
		mainView 906
		mainLoop 1
		mainCel 5
		value 6
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 95))
						(
							(or
								(ego mover?)
								(and
									(ego cycler?)
									(not ((ego cycler?) isKindOf: StopWalk))
								)
							)
							(messager say: 0 0 32 1 0 21)
						)
						((super doVerb: 4)
							(if (OneOf curRoomNum 270 340 545 750 730 545)
								(curRoom doVerb: 95)
							else
								(soundFX number: 934 play:)
								(ego hide:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance protectionSpell of SpellItem
	(properties
		noun 31
		mainView 906
		mainLoop 1
		mainCel 2
		value 7
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 97))
						((super doVerb: 4)
							(if (OneOf curRoomNum 270 340 750)
								(curRoom doVerb: 97)
							else
								(= protectTimer (* [egoStats 38] 4))
								(soundFX number: 934 play:)
								(messager say: 0 0 29 1 0 21)
								((ScriptID 0 21) doit:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance auraSpell of SpellItem
	(properties
		noun 29
		mainView 906
		mainLoop 1
		value 8
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 96))
						((super doVerb: 4)
							(if (OneOf curRoomNum 270 340 750)
								(curRoom doVerb: 96)
							else
								(= auraTimer (* [egoStats 37] 4))
								(soundFX number: 934 play:)
								(messager say: 0 0 26 1 0 21)
								((ScriptID 0 21) doit:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance resistanceSpell of SpellItem
	(properties
		noun 30
		mainView 906
		mainLoop 1
		mainCel 4
		value 10
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 98))
						((super doVerb: 4)
							(if (OneOf curRoomNum 270 340 670 750)
								(curRoom doVerb: 98)
							else
								(= resistTimer 30)
								(soundFX number: 934 play:)
								(messager say: 0 0 30 1 0 21)
								((ScriptID 0 21) doit:)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
)

(instance glideSpell of SpellItem
	(properties
		noun 28
		mainView 906
		mainCel 15
		value 10
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(glorySpells hide:)
					(cond 
						((or (== curRoomNum 750) (== curRoomNum 340)) (curRoom notify: 11))
						((super doVerb: 4)
							(if
								(OneOf
									curRoomNum
									270
									340
									440
									530
									535
									541
									542
									543
									545
									520
									561
									563
									564
									570
									571
									577
									581
									588
									593
									750
									790
								)
								(curRoom doVerb: 11)
							else
								(messager say: 28 0 25 0 0 21)
							)
						)
						(else (return 1))
					)
				)
				(1 (super doVerb: theVerb))
			)
		)
	)
	
	(method (select)
		(self doVerb: 4)
		(return 0)
	)
)

(instance soundFX of Sound
	(properties)
)
