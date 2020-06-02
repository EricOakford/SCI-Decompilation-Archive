;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use wizGameMazeBug)
(use Intrface)
(use KeyCursor)
(use Procs)
(use Print)
(use ForCount)
(use LoadMany)
(use Cat)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	wizGame 0
	playerBug 1
	wizBug 2
	proc32_3 3
	proc32_4 4
)

(local
	local0
	local1
	theSeconds =  4
	local3
	local4
	theFetchable
	local6
	local7
	local8
	local9
	theGStrength
	local11
	[local12 2]
	local14
	[theFetchable_2 8]
	[newFetchable 17]
	[newFetchable_2 7]
	[local47 24] = [40 20 0 274 23 0 201 50 0 130 86 0 96 117 0 230 108 0 150 143 0 250 130]
	[local71 51] = [79 6 3 137 6 3 223 6 3 28 38 1 56 58 5 117 43 2 156 47 4 225 28 3 290 39 1 266 54 1 114 93 6 182 101 1 235 136 2 273 138 4 500 500 0 500 500 0 500 500]
	[local122 21] = [82 41 1 49 81 1 185 85 1 279 81 1 196 129 1 500 500 0 500 500]
	[local143 78] = [0 0 110 14 33 25 105 0 229 14 191 15 230 0 319 14 272 25 0 14 60 75 57 95 184 40 233 65 226 66 63 14 225 75 152 76 223 14 319 75 284 96 0 74 82 97 173 144 80 74 168 97 61 120 166 74 219 95 183 96 244 74 319 107 195 113 0 117 79 154 195 113 172 92 200 154 320 146]
	[local221 4] = [48 122 198 274]
	[local225 9] = [0 0 10 10 10 0 -10 -10 -10]
	[local234 9] = [6 -6 -6 0 6 6 6 0 -6]
)
(procedure (proc32_3)
	(asm
		bnot    
		pushi    2
		pushi    500
		dup     
		pushi    161
		pushi    1
		pushi    0
		pushi    63
		pushi    1
		pushi    15
		pushi    313
		pushi    0
		lofsa    flame
		send     24
		jmp      code_0453
		dup     
		ldi      24
		eq?     
		bnt      code_0453
		lal      theFetchable
		bnt      code_0448
		pushi    #drop
		pushi    2
		pushi    #x
		pushi    0
		send     4
		push    
		pushi    #y
		pushi    0
		lal      theFetchable
		send     4
		push    
		lal      theFetchable
		send     8
		ldi      0
		sal      theFetchable
code_0448:
		pushi    #setCursor
		pushi    2
		lsg      normalCursor
		pushi    1
		lag      theGame
		send     8
code_0453:
		toss    
		ldi      0
		sal      local4
		ret     
	)
)

(procedure (proc32_4)
;;;	(asm
;;;		bnot    
;;;		pushi    2
;;;		pushi    500
;;;		dup     
;;;		pushi    161
;;;		pushi    1
;;;		pushi    0
;;;		pushi    63
;;;		pushi    1
;;;		pushi    15
;;;		pushi    313
;;;		pushi    0
;;;		lofsa    flame
;;;		send     24
;;;		jmp      code_0453
;;;		dup     
;;;		ldi      24
;;;		eq?     
;;;		bnt      code_0453
;;;		lal      theFetchable
;;;		bnt      code_0448
;;;		pushi    #drop
;;;		pushi    2
;;;		pushi    #x
;;;		pushi    0
;;;		send     4
;;;		push    
;;;		pushi    #y
;;;		pushi    0
;;;		lal      theFetchable
;;;		send     4
;;;		push    
;;;		lal      theFetchable
;;;		send     8
;;;		ldi      0
;;;		sal      theFetchable
;;;code_0448:
;;;		pushi    #setCursor
;;;		pushi    2
;;;		lsg      normalCursor
;;;		pushi    1
;;;		lag      theGame
;;;		send     8
;;;code_0453:
;;;		toss    
;;;		ldi      0
;;;		sal      local4
;;;		ret     
;;;	)
)

(procedure (localproc_0294)
	(button1 init:)
	(objectList add: button1)
	(button2 init:)
	(objectList add: button2)
	(button3 init:)
	(objectList add: button3)
	(button4 init:)
	(objectList add: button4)
)

(procedure (localproc_02e5 param1 param2 param3 param4 param5 param6)
	(return
		(if
			(and
				(<= param3 param1)
				(< param1 param5)
				(<= param4 param2)
			)
			(< param2 param6)
		else
			0
		)
	)
)

(procedure (localproc_0300 param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2)
	(= local7 (= local9 1000))
	(= local6 (= local8 param3))
	(= temp0 param3)
	(= temp1 (+ param3 param3 param3))
	(while (< temp0 param4)
		(cond 
			(
			(and param5 (< [local47 (+ temp1 1)] (- param2 5))))
			(
				(<
					(= temp2
						(GetDistance
							param1
							param2
							[local47 temp1]
							[local47 (+ temp1 1)]
						)
					)
					local7
				)
				(if (< local7 local9)
					(= local9 local7)
					(= local8 local6)
				)
				(= local7 temp2)
				(= local6 temp0)
			)
		)
		(++ temp0)
		(= temp1 (+ temp1 3))
	)
	(return local6)
)

(procedure (localproc_037a param1)
	(return
		(if (ego knows: param1)
			(if (CastSpell param1)
				(localproc_03b2 param1)
				(return 1)
			else
				(return 0)
			)
		else
			(messager say: 4 0 1)
			(return 0)
		)
	)
)

(procedure (localproc_03b2 param1)
	(if local4 (localproc_03e5))
	(= local4 param1)
	(= local3 (GetTime 1))
	(if (!= local4 19)
		(= dftStatusCode (+ 2 (/ [egoStats param1] 10)))
		(timeBar value: dftStatusCode draw:)
	)
)

(procedure (localproc_03e5 &tmp [temp0 2])
	(= dftStatusCode 0)
	(if (!= local4 19) (timeBar value: dftStatusCode draw:))
	(switch local4
		(23
			(flame posn: 500 500 setCycle: 0 setPri: 15 stopUpd:)
		)
		(24
			(if theFetchable
				(theFetchable drop: (theFetchable x?) (theFetchable y?))
				(= theFetchable 0)
			)
			(theGame setCursor: normalCursor 1)
		)
	)
	(= local4 0)
)

(procedure (localproc_125f param1 param2 param3 &tmp temp0 temp1)
	(-- param3)
	(repeat
		(if
		[theFetchable_2 (= temp0 (Random param2 param3))]
			(break)
		)
	)
	(= [theFetchable_2 param1] [theFetchable_2 temp0])
	([theFetchable_2 temp0] listIndex: param1)
	(= [theFetchable_2 temp0] 0)
	(= temp1 (+ param1 param1 param1))
	([theFetchable_2 param1]
		setCel: [local47 (+ temp1 2)]
		setPri: 1
		posn: [local47 temp1] [local47 (+ temp1 1)]
	)
)

(procedure (localproc_12bc param1 param2 &tmp temp0 theTheFetchable_2)
	(objectList empty:)
	(= temp0 param1)
	(while (< temp0 param2)
		(if (= theTheFetchable_2 [theFetchable_2 temp0])
			(objectList
				add:
					((CursorCoords new:)
						cursorX: (theTheFetchable_2 x?)
						cursorY: (theTheFetchable_2 y?)
					)
			)
		)
		(++ temp0)
	)
)

(procedure (localproc_1307 param1 param2 &tmp temp0)
	(objectList empty:)
	(= temp0 param1)
	(while (< temp0 param2)
		(if (not [theFetchable_2 temp0])
			(objectList
				add:
					((CursorCoords new:)
						cursorX: [theFetchable_2 (+ 32 temp0 temp0 temp0)]
						cursorY: [theFetchable_2 (+ 33 temp0 temp0 temp0)]
					)
			)
		)
		(++ temp0)
	)
)

(procedure (localproc_193a param1 &tmp temp0 theTheFetchable_2 temp2)
	(if (= theTheFetchable_2 [theFetchable_2 param1])
		(repeat
			(if (not [theFetchable_2 (= temp2 (Random 0 7))])
				(break)
			)
		)
		(= [theFetchable_2 temp2] theTheFetchable_2)
		(theTheFetchable_2 listIndex: temp2)
		(= [theFetchable_2 param1] 0)
		(= temp0 (+ temp2 temp2 temp2))
		(theTheFetchable_2
			posn: [theFetchable_2 (+ 32 temp0)] [theFetchable_2 (+ 33 temp0)]
		)
	)
)

(procedure (localproc_19f9 param1 param2 param3 &tmp temp0 temp1 temp2 theTheFetchable_2 temp4)
	(switch param2
		(3
			(= temp0 (= temp2 0))
			(= temp1 8)
		)
		(2
			(= temp0 (= temp2 8))
			(= temp1 25)
		)
		(1
			(= temp0 (= temp2 25))
			(= temp1 32)
		)
	)
	(= temp4 0)
	(while (< temp0 temp1)
		(if
			(and
				(= theTheFetchable_2 [theFetchable_2 temp0])
				(<= (param1 distanceTo: theTheFetchable_2) param3)
			)
			(= temp4 1)
			(break)
		)
		(++ temp0)
	)
	(return temp4)
)

(class Rock of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		listIndex 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event message?) KEY_ESCAPE)
					(not (Btst 337))
				)
				(Bset 337)
				(= local14 0)
				(wizGame cue:)
				(event claimed: 1)
			)
			(
				(and
					(== local4 17)
					(or
						(MousedOn self event 0)
						(and
							(== (event message?) KEY_RETURN)
							(== (event type?) keyDown)
							(== x (event x?))
							(== y (event y?))
						)
					)
				)
				(event claimed: 1)
				(= dftStatusCode 0)
				(localproc_193a listIndex)
				(localproc_03e5)
				(= global188 1)
				(wizGame setScript: 0)
			)
		)
	)
)

(instance lasso of Cat
	(properties
		x 400
		y 300
		view 32
		loop 8
		illegalBits $0000
		doCast 1
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event message?) KEY_ESCAPE)
					(not (Btst 337))
				)
				(Bset 337)
				(= local14 0)
				(wizGame cue:)
				(event claimed: 1)
			)
			(
				(and
					(== local4 24)
					(== (event message?) ENTER)
					(== (event type?) keyDown)
				)
				(event type: 2)
			)
		)
		(super handleEvent: event)
	)
	
	(method (posn)
		(super posn: &rest)
		(if theFetchable (theFetchable posn: &rest))
	)
)

(class Fetchable of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		listIndex 0
		fetchType 0
		inMotion 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(if (self inMotion?) (self drop:))
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event message?) KEY_ESCAPE)
					(not (Btst 337))
				)
				(Bset 337)
				(= local14 0)
				(wizGame cue:)
				(event claimed: 1)
			)
			(
				(and
					(not inMotion)
					(== local4 24)
					(or
						(and
							(MousedOn self event 0)
							(!= (event type?) mouseUp)
						)
						(and
							(== (event message?) ENTER)
							(== (event type?) keyDown)
							(== x (event x?))
							(== y (event y?))
						)
					)
				)
				(event claimed: 1)
				(self pickUp: event)
			)
		)
	)
	
	(method (pickUp param1)
		(if (not inMotion)
			(if (== fetchType (= inMotion 1))
				(= [theFetchable_2 listIndex] 0)
				(= local1 1)
				(localproc_1307 25 32)
			else
				(= [theFetchable_2 listIndex] 0)
				(= local0 1)
				(localproc_1307 8 25)
			)
			(= theFetchable self)
			(self setCel: 0 setPri: 10 startUpd:)
			(lasso
				setLoop: 8
				setPri: 11
				ignoreActors: 1
				init:
				posn: x y z
				setCycle: Forward
				caller: self
				track: param1
			)
		)
	)
	
	(method (drop param1 param2 &tmp temp0 temp1 temp2)
		(= temp0 (OnControl 4 param1 param2))
		(switch fetchType
			(1
				(= temp1 (localproc_0300 param1 param2 25 32 0))
			)
			(2
				(= temp1 (localproc_0300 param1 param2 8 25 0))
			)
		)
		(= inMotion (= theFetchable (= local4 0)))
		(lasso dispose:)
		(theGame setCursor: normalCursor 1)
		(if (== temp1 -1)
			(self dispose:)
		else
			(= [theFetchable_2 temp1] self)
			(self listIndex: temp1)
			(= temp2 (+ temp1 temp1 temp1))
			(self
				setCel: [local47 (+ temp2 2)]
				setPri: 1
				posn: [local47 temp2] [local47 (+ temp2 1)]
				stopUpd:
			)
		)
		(= local0 0)
		(= local1 0)
		(objectList empty:)
		(localproc_03e5)
		(= global188 1)
		(wizGame setScript: 0)
	)
	
	(method (cue)
		(self drop: x y)
	)
)

(class SpecialBug of MazeBug
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 2
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		startX 0
		startY 0
		deadTime 0
		otherBug 0
		form 0
		smarts 5
		path 0
		onALadder 0
	)
	
	(method (doit)
		(super doit:)
		(if (or (>= x 315) (>= y 190))
			(mover completed: 1)
			(= local14 0)
			(wizGame cue:)
		else
			(self chooseRoute: 0)
		)
	)
	
	(method (cue)
		(= onALadder 0)
		(self chooseRoute: 1)
	)
	
	(method (nearRock param1)
		(localproc_19f9 self 3 param1)
	)
	
	(method (nearBridge param1)
		(localproc_19f9 self 2 param1)
	)
	
	(method (nearLadder param1)
		(localproc_19f9 self 1 param1)
	)
	
	(method (chooseRoute param1 &tmp theSpecialBugPath specialBugPath temp2 temp3 temp4 temp5)
		(if
			(and
				dftStatusCode
				(== local4 23)
				(< (self distanceTo: flame) 120)
			)
			(if
				(or
					(not mover)
					(!= (mover x?) (flame x?))
					(!= (mover y?) (flame y?))
				)
				(self setMotion: MazeMove (flame x?) (flame y?))
			)
			(return)
		)
		(if (== form 1)
			(if (and mover onALadder) (return))
			(= temp2
				[local47 (= temp5
					(+ (= temp4 (localproc_0300 x y 25 32 1)) temp4 temp4)
				)]
			)
			(= temp3 [local47 (+ temp5 1)])
			(if
				(and
					(== (self onControl: 1) 8)
					(or
						(not mover)
						(!= (mover x?) temp2)
						(!= (mover y?) (+ temp3 10))
					)
				)
				(self
					path: 0
					onALadder: 1
					setMotion: MazeMove temp2 (+ temp3 10) self
				)
				(return)
			)
		)
		(if
			(and
				(not param1)
				mover
				(self
					inRect:
						[local143 path]
						[local143 (+ path 1)]
						[local143 (+ path 2)]
						[local143 (+ path 3)]
				)
			)
			(return)
		)
		(= specialBugPath (self path?))
		(= theSpecialBugPath 0)
		(while (< theSpecialBugPath 78)
			(if
				(localproc_02e5
					x
					y
					[local143 theSpecialBugPath]
					[local143 (+ theSpecialBugPath 1)]
					[local143 (+ theSpecialBugPath 2)]
					[local143 (+ theSpecialBugPath 3)]
				)
				(break)
			)
			(= theSpecialBugPath (+ theSpecialBugPath 6))
		)
		(if (== theSpecialBugPath 78)
			(if mover
				(return)
			else
				(= theSpecialBugPath specialBugPath)
			)
		)
		(self
			path: theSpecialBugPath
			setMotion:
				MazeMove
				[local143 (+ theSpecialBugPath 4)]
				[local143 (+ theSpecialBugPath 5)]
				self
		)
	)
)

(instance playerBug of SpecialBug
	(properties
		view 33
		startX 69
		startY 9
		smarts 3
	)
)

(instance wizBug of SpecialBug
	(properties
		view 33
		loop 3
		startX 293
		startY 5
		smarts 8
	)
	
	(method (changeForm)
		(if (or (!= form 1) (!= (self onControl: 1) 4))
			(super changeForm:)
		)
	)
)

(instance gameSound of Sound
	(properties
		flags $ffff
		number 79
		priority 2
		loop -1
	)
)

(instance flame of Prop
	(properties
		x 500
		y 500
		view 32
		loop 6
	)
)

(instance fetchSign of Prop
	(properties
		x 75
		y 165
		view 32
		priority 5
		cycleSpeed 4
	)
)

(instance openSign of Prop
	(properties
		x 122
		y 169
		view 32
		loop 1
		cel 2
		priority 5
		cycleSpeed 4
	)
)

(instance trigSign of Prop
	(properties
		x 182
		y 183
		view 32
		loop 2
		cel 2
		priority 5
		cycleSpeed 4
	)
)

(instance flameSign of Prop
	(properties
		x 225
		y 173
		view 32
		loop 7
		cel 2
		priority 5
		cycleSpeed 4
	)
)

(class pointBar of View
	(properties
		x 23
		y 165
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 32
		loop 9
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		value 0
		valCel 0
		max 1000
	)
	
	(method (init)
		(self ignoreActors: draw:)
		(super init: &rest)
	)
	
	(method (draw &tmp theValue)
		(if (< max 1) (= max 1))
		(if (> (= theValue value) max) (= theValue max))
		(= valCel
			(/
				(+ (= theValue (/ (+ (* theValue 100) max -1) max)) 9)
				10
			)
		)
		(self cel: valCel setPri: 15 stopUpd:)
	)
)

(instance timeBar of pointBar
	(properties
		x 293
		loop 10
		max 10
	)
	
	(method (draw)
		(super draw:)
		(if (== valCel 10) (candleFlame setCycle: Forward))
		(if (> valCel 2)
			(candleFlame posn: (+ x 2) (+ y (- 17 valCel)))
		else
			(candleFlame posn: 500 500 setCycle: 0 stopUpd:)
		)
	)
)

(instance candleFlame of Prop
	(properties
		x 500
		y 500
		view 32
		loop 11
		cycleSpeed 16
	)
)

(instance wizGame of Room
	(properties
		picture 32
		style $0006
	)
	
	(method (init &tmp temp0 temp1 temp2 temp3 temp4)
		(LoadMany 130 238 205 810)
		(LoadMany 128 32 33 803)
		Cat
		ForwardCounter
		(super init:)
		(theIconBar disable: 1 2 3 4 5 6 7 8)
		(theGame setCursor: 942 1)
		(Bset 359)
		(++ magesMazePlayCount)
		(SolvePuzzle 616 5 1)
		(= local14 1)
		(mouseDownHandler add: cast features)
		(candleFlame init: stopUpd:)
		(= temp0 0)
		(while (< temp0 4)
			(repeat
				(if (not [theFetchable_2 (= temp2 (Random 0 7))])
					(break)
				)
			)
			(= temp3 [local47 (= temp1 (+ temp2 temp2 temp2))])
			(= temp4 [local47 (+ temp1 1)])
			((= [theFetchable_2 temp2] (Rock new:))
				listIndex: temp2
				view: 32
				setLoop: 3
				setCel: 0
				setPri: 6
				x: temp3
				y: temp4
				ignoreActors: 1
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 5)
			(repeat
				(if (not [newFetchable (= temp2 (Random 0 13))])
					(break)
				)
			)
			(= temp1 (+ temp2 temp2 temp2))
			((= [newFetchable temp2] (Fetchable new:))
				listIndex: (+ temp2 8)
				fetchType: 2
				view: 32
				setLoop: 4
				setCel: [local71 (+ temp1 2)]
				setPri: 1
				ignoreActors: 1
				posn: [local71 temp1] [local71 (+ temp1 1)]
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 2)
			(repeat
				(if (not [newFetchable_2 (= temp2 (Random 0 4))])
					(break)
				)
			)
			(= temp1 (+ temp2 temp2 temp2))
			((= [newFetchable_2 temp2] (Fetchable new:))
				listIndex: (+ temp2 25)
				fetchType: 1
				view: 32
				setLoop: 5
				setCel: [local122 (+ temp1 2)]
				setPri: 1
				ignoreActors: 1
				posn: [local122 temp1] [local122 (+ temp1 1)]
				init:
				stopUpd:
			)
			(++ temp0)
		)
		(Bclr 337)
		(fetchSign init: stopUpd:)
		(openSign init: stopUpd:)
		(trigSign init: stopUpd:)
		(flameSign init: stopUpd:)
		(pointBar max: (MaxMana) value: [egoStats 16] init:)
		(= dftStatusCode 0)
		(timeBar init:)
		(playerBug
			setLoop: 0
			setCycle: Forward
			posn: 69 9
			lowPri:
			smarts: (/ (+ [egoStats 1] 5) 10)
			otherBug: wizBug
			path: 0
			init:
			baseSetter: baseCode
			setMotion: MazeMove [local143 4] [local143 5] playerBug
			setScript: timeScript
		)
		(wizBug
			setLoop: 3
			setCycle: Forward
			posn: 289 6
			lowPri:
			otherBug: playerBug
			path: 12
			init:
			baseSetter: baseCode
			setMotion: MazeMove [local143 16] [local143 17] wizBug
			setScript: wizScript
		)
		(flame setPri: 15 init: setCycle: Forward stopUpd:)
		(gameSound init: play:)
		(user canInput: 0)
		(directionHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(objectList add:)
		(= global188 1)
		(Joystick 12 30)
	)
	
	(method (doit &tmp temp0 temp1)
		(if (!= theGStrength [egoStats 16])
			(= theGStrength [egoStats 16])
			(pointBar value: theGStrength draw:)
		)
		(if
		(and dftStatusCode (!= (= temp0 (GetTime 1)) local3))
			(= local3 temp0)
			(if (-- dftStatusCode)
				(timeBar value: dftStatusCode draw:)
			else
				(localproc_03e5)
				(= global188 1)
				(wizGame setScript: 0)
			)
		)
		(if global188
			(= temp1 global188)
			(= global188 0)
			(switch temp1
				(1
					(self setScript: chooseSpell)
				)
				(2
					(self setScript: doASpell 0 24)
				)
				(3
					(self setScript: doASpell 0 17)
				)
				(4
					(self setScript: doASpell 0 19)
				)
				(5 (self setScript: doFlame))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(timeBar dispose:)
		(pointBar dispose:)
		(objectList release: dispose:)
		(if script (script dispose:))
		(directionHandler delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: cast features)
		(theIconBar enable:)
		(theIconBar enable: 1 2 3 4 5 6 7 8)
		(Bclr 359)
		(if (HaveMouse)
			(theGame setCursor: normalCursor 1)
		else
			(theGame setCursor: normalCursor 1 304 174)
		)
		(Joystick 12 0)
		(LoadMany 0 238 205 810 976)
		(DisposeScript 956)
		(super dispose:)
	)
	
	(method (cue &tmp temp0 temp1 [temp2 150])
		(cond 
			(local11 (curRoom newRoom: 31))
			(local14 (= local14 0) (messager say: 4 0 6 1))
			((Btst 337) (= local11 1) (messager say: 4 0 4 1 self))
			(((wizBug mover?) completed?) (= local11 1) (messager say: 4 0 5 0 self))
			((not (ego knows: 20))
				(= local11 1)
				(if
					(<
						(= temp0 (+ magesMazePlayCount magesMazePlayCount))
						500
					)
					(= temp0 500)
				)
				(= temp1 (if (== magesMazePlayCount 1) {} else {s}))
				(Printf
					@temp2
					{"Well, you seem to have beginner's luck.__It only took you %d game%s to win.__Hmph.__It usually takes at least %d tries for anyone less than a full Wizard to defeat me."}
					magesMazePlayCount
					temp1
					temp0
				)
				(ego learn: 20 10)
				(SolvePuzzle 617 12 1)
				(messager say: 4 0 2 0 self)
			)
			(else (= local11 1) (messager say: 4 0 3 0 self))
		)
	)
)

(instance timeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(if (ego has: 13) (messager say: 1))
			)
		)
	)
)

(instance wizScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 clientX clientY clientForm temp5 temp6 temp7)
		(switch (= state newState)
			(0 (= seconds theSeconds))
			(1
				(= clientX (client x?))
				(= clientY (client y?))
				(= clientForm (client form?))
				(= temp1 (localproc_0300 clientX clientY 0 32 1))
				(= temp6 local7)
				(= temp7 1)
				(while (< temp7 3)
					(= temp5 (+ temp1 temp1 temp1))
					(++ temp7)
					(cond 
						(
							(and
								(< (client distanceTo: playerBug) 25)
								(<= -1 (- clientForm (playerBug form?)))
								(<= (- clientForm (playerBug form?)) 0)
							)
							(client changeForm:)
						)
						(
						(and (!= clientForm 0) (== (client onControl: 1) 1024)) (client changeForm:))
						((> temp6 25))
						(
						(and (< 0 temp1) (< temp1 8) [theFetchable_2 temp1])
							(localproc_193a temp1)
							(if (== local4 17) (localproc_12bc 0 8))
						)
						(
							(and
								(< 8 temp1)
								(< temp1 25)
								(not [theFetchable_2 temp1])
							)
							(localproc_125f temp1 8 25)
							(if (== local4 24)
								(if local0
									(localproc_1307 8 25)
								else
									(localproc_12bc 8 25)
								)
							)
						)
						((and (< 25 temp1) (< temp1 32))
							(cond 
								((not [theFetchable_2 temp1])
									(localproc_125f temp1 25 32)
									(if (== local4 24)
										(if local1
											(localproc_1307 25 32)
										else
											(localproc_12bc 25 32)
										)
									)
								)
								((!= clientForm 1) (client changeForm:))
								(else (-- temp7))
							)
						)
						(else (-- temp7))
					)
					(self changeState: 0)
					(++ temp7)
					(= temp6 local9)
					(= temp1 local8)
				)
			)
		)
	)
)

(instance objectList of InputList
	(properties)
)

(instance chooseSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (HaveMouse))
					(theGame setCursor: theCursor 1 [local221 global187] 175)
				)
				(objectList empty:)
				(localproc_0294)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 3] eventX eventY)
		(if
			(and
				(== (event message?) KEY_RETURN)
				(== (event type?) keyDown)
			)
			(event type: 1)
		)
		(return
			(cond 
				(
					(and
						(== (event message?) ESC)
						(not (Btst 337))
					)
					(Bset 337)
					(= local14 0)
					(wizGame cue:)
					(self dispose:)
				)
				(
					(and
						(<= JOY_UP (event message?))
						(<= (event message?) JOY_UPLEFT)
					)
					(objectList handleEvent: event)
				)
				((== (event type?) mouseDown)
					(= eventX (event x?))
					(= eventY (event y?))
					(event claimed: 1)
					(cond 
						((localproc_02e5 eventX eventY 65 150 85 185)
							(if (<= [egoStats 16] 0)
								(if (not local14) (Bset 337) (self dispose:))
								(wizGame cue:)
							else
								(= global187 0)
								(= global188 2)
								(self dispose:)
							)
							(return 1)
						)
						((localproc_02e5 eventX eventY 112 154 132 184)
							(if (<= [egoStats 16] 0)
								(if (not local14) (Bset 337) (self dispose:))
								(wizGame cue:)
							else
								(= global187 1)
								(= global188 3)
								(self dispose:)
							)
						)
						((localproc_02e5 eventX eventY 172 168 192 198)
							(if (<= [egoStats 16] 0)
								(if (not local14) (Bset 337) (self dispose:))
								(wizGame cue:)
							else
								(= global187 2)
								(= global188 4)
								(self dispose:)
							)
						)
						((localproc_02e5 eventX eventY 215 155 235 190)
							(if (<= [egoStats 16] 0)
								(if (not local14) (Bset 337) (self dispose:))
								(wizGame cue:)
							else
								(= global187 3)
								(= global188 5)
								(self dispose:)
							)
						)
						((localproc_02e5 eventX eventY 7 158 43 188)
							(if (ego has: 13)
								(= local14 1)
								(UseMana (- (/ (MaxMana) 2)))
								(ego use: 13)
								(ego get: 11)
								(theIconBar disable: 6)
								(messager say: 2)
							else
								(messager say: 3)
							)
						)
						(else (event claimed: 0))
					)
				)
			)
		)
	)
)

(instance button1 of CursorCoords
	(properties
		cursorX 75
		cursorY 175
	)
)

(instance button2 of CursorCoords
	(properties
		cursorX 122
		cursorY 175
	)
)

(instance button3 of CursorCoords
	(properties
		cursorX 182
		cursorY 175
	)
)

(instance button4 of CursorCoords
	(properties
		cursorX 225
		cursorY 175
	)
)

(instance doASpell of Script
	(properties)
	
	(method (dispose)
		(objectList empty:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (localproc_037a register)
					(switch register
						(17
							(localproc_12bc 0 8)
							(= register openSign)
							(register setCycle: EndLoop self)
						)
						(24
							(localproc_12bc 8 32)
							(= register fetchSign)
							(register setCycle: ForwardCounter 3 self)
						)
						(19
							(= register trigSign)
							(register setCycle: EndLoop self)
						)
					)
				else
					(= global188 1)
					(self dispose:)
				)
			)
			(1
				(register stopUpd:)
				(if (== register trigSign)
					(playerBug changeForm:)
					(localproc_03e5)
					(= global188 1)
					(self dispose:)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event message?) KEY_ESCAPE)
					(not (Btst 337))
				)
				(= local14 0)
				(Bset 337)
				(wizGame cue:)
				(event claimed: 1)
				(self dispose:)
			)
			(
				(and
					(<= JOY_UP (event message?))
					(<= (event message?) JOY_UPLEFT)
				)
				(objectList handleEvent: event)
			)
		)
	)
)

(instance doFlame of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (localproc_037a 23)
					(HandsOff)
					(= dftStatusCode 0)
					(theGame setCursor: normalCursor 1)
					(flameSign setCycle: EndLoop self)
				else
					(= global188 1)
					(self dispose:)
				)
			)
			(1
				(flameSign stopUpd:)
				(HandsOn)
				(User canInput: 0)
				(theGame setCursor: normalCursor 1)
				(flame posn: 225 170 setCycle: Forward)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 eventMessage)
		(cond 
			(
				(and
					(== (event message?) ESC)
					(not (Btst 337))
				)
				(= local14 0)
				(Bset 337)
				(wizGame cue:)
				(event claimed: 1)
				(self dispose:)
			)
			(
				(and
					(<= JOY_UP (event message?))
					(<= (event message?) JOY_UPLEFT)
				)
				(= temp1 [local225 (= eventMessage (event message?))])
				(= temp2 [local234 eventMessage])
				(cond 
					((and (<= (flame x?) 0) (< temp1 0)) (= temp1 0))
					((and (<= 319 (flame x?)) (< 0 temp1)) (= temp1 0))
					((and (<= (flame y?) 0) (< temp2 0)) (= temp2 0))
					((and (<= 189 (flame y?)) (< 0 temp2)) (= temp2 0))
				)
				(flame
					posn: (+ (flame x?) temp1) (+ (flame y?) temp2)
				)
				(event claimed: 1)
			)
			((== (event type?) mouseDown)
				(event claimed: 1)
				(= dftStatusCode (+ 4 (/ [egoStats 23] 5)))
				(flame posn: (event x?) (event y?) setCycle: Forward)
				(= global188 1)
				(self dispose:)
			)
			(
				(and
					(== (event message?) KEY_RETURN)
					(== (event type?) keyDown)
				)
				(flame setPri: -1)
				(event claimed: 1)
				(= dftStatusCode (+ 4 (/ [egoStats 23] 5)))
				(= global188 1)
				(self dispose:)
			)
		)
	)
)

(instance baseCode of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1)
		(= temp0 (param1 x?))
		(= temp1 (param1 y?))
		(param1
			brTop: (- temp1 2)
			brBottom: (+ temp1 2)
			brLeft: (- temp0 2)
			brRight: (+ temp0 2)
		)
	)
)
