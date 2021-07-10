;;; Sierra Script 1.0 - (do not remove this comment)
(script# 39)
(include game.sh)
(use Main)
(use Intrface)
(use ForCount)
(use LoadMany)
(use Count)
(use Sort)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	diceRm 0
	proc39_1 1
	johnnyDice 2
	oldSaltDice 3
	johnnyRArmP 4
	johnnyLArmP 5
	saltRArmP 6
	saltLArmP 7
	johnnyMouth 8
	saltMouth 9
	objList 10
	cButton 11
	rButton 12
	nButton 13
	johnnyMoney 14
	oldSaltMoney 15
	johnTwitch 16
	smokeCigar 17
	greenTumble 18
	greenTable 19
	redTumble 20
	redTable 21
	proc39_22 22
	soundDice 23
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6 =  1
	local7 = [180 0 45 90 135 180 225 270 315]
	local16
	local17
	local18
	local19
	local20
	numReplays
)
(procedure (proc39_1)
	(if (> (oldSaltDice diceScore?) 500)
		(oldSaltDice selected: 0)
	else
		(oldSaltDice selected: 1)
		(cond 
			((> (oldSaltDice diceScore?) 400)
				(if
					(==
						((oldSaltDice at: 0) cel?)
						((oldSaltDice at: 2) cel?)
					)
					((oldSaltDice at: 4) select: 1)
				else
					((oldSaltDice at: 0) select: 1)
				)
			)
			((> (oldSaltDice diceScore?) 200)
				(cond 
					(
						(==
							((oldSaltDice at: 0) cel?)
							((oldSaltDice at: 2) cel?)
						)
						((oldSaltDice at: 3) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
					(
						(==
							((oldSaltDice at: 4) cel?)
							((oldSaltDice at: 2) cel?)
						)
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 1) select: 1)
					)
					(else
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
				)
			)
			((> (oldSaltDice diceScore?) 100)
				(cond 
					(
						(==
							((oldSaltDice at: 0) cel?)
							((oldSaltDice at: 1) cel?)
						)
						((oldSaltDice at: 2) select: 1)
						((oldSaltDice at: 3) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
					(
						(==
							((oldSaltDice at: 1) cel?)
							((oldSaltDice at: 2) cel?)
						)
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 3) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
				)
			)
			((> (oldSaltDice diceScore?) 0)
				(cond 
					(
						(==
							((oldSaltDice at: 0) cel?)
							((oldSaltDice at: 1) cel?)
						)
						((oldSaltDice at: 2) select: 1)
						((oldSaltDice at: 3) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
					(
						(==
							((oldSaltDice at: 1) cel?)
							((oldSaltDice at: 2) cel?)
						)
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 3) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
					(
						(==
							((oldSaltDice at: 2) cel?)
							((oldSaltDice at: 3) cel?)
						)
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 1) select: 1)
						((oldSaltDice at: 4) select: 1)
					)
					(else
						((oldSaltDice at: 0) select: 1)
						((oldSaltDice at: 1) select: 1)
						((oldSaltDice at: 2) select: 1)
					)
				)
			)
			(else
				((oldSaltDice at: 1) select: 1)
				((oldSaltDice at: 2) select: 1)
				((oldSaltDice at: 3) select: 1)
				((oldSaltDice at: 4) select: 1)
			)
		)
	)
)

(procedure (proc39_22 param1 &tmp node nextNode ret)
	(= ret 0)
	(= node (FirstNode (param1 elements?)))
	(while node
		(= nextNode (NextNode node))
		(if ((NodeValue node) selected?) (++ ret))
		(= node nextNode)
	)
	(return ret)
)

(procedure (localproc_1ae6 param1 &tmp temp0)
	(= temp0 210)
	(while (< temp0 300)
		(objList
			add: ((CursorCoords new:) cursorX: temp0 cursorY: 17)
		)
		(= temp0 (+ temp0 20))
	)
	(objList
		add: ((CursorCoords new:) cursorX: 250 cursorY: 33)
	)
	(if param1
		(objList
			add: ((CursorCoords new:) cursorX: 230 cursorY: 33)
		)
		(objList
			add: ((CursorCoords new:) cursorX: 270 cursorY: 33)
		)
	)
)

(instance dieBox of View
	(properties
		view 39
		loop 2
	)
)

(instance rollDieScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: (Random 0 (client lastCel:)) rolling: 1)
				(client hide: select: 0)
			)
			(1 (= seconds 2))
			(2
				(client rolling: 0 setScript: 0)
			)
		)
	)
)

(class Die of Prop
	(properties
		selected 0
		mousable 1
		box 0
		rolling 0
	)
	
	(method (init)
		(super init:)
		((= box (Clone dieBox))
			x: (self x?)
			y: (self y?)
			stopUpd:
			init:
		)
		(self select: selected)
		(self stopUpd:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and mousable (MousedOn self event))
				(self select:)
				(event claimed: TRUE)
			)
		)
	)
	
	(method (roll)
		(if selected
			(self setScript: (Clone rollDieScript))
		)
	)
	
	(method (select param1)
		(if (= selected (if argc param1 else (not selected)))
			(box show:)
		else
			(box hide:)
		)
	)
)

(class DiceGame of List
	(properties
		selected 0
		view 0
		loop 0
	)
	
	(method (init param1 &tmp i newDie)
		(if argc
			(for ((= i 0)) (<= i (- argc 2)) ((++ i))
				((= newDie (Die new:))
					posn: [param1 i] [param1 (++ i)]
					view: view
					loop: loop
					init:
					hide:
				)
				(newDie setCel: (Random 0 (newDie lastCel:)))
				(self add: newDie)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(self release:)
		(super dispose:)
	)
	
	(method (cue)
		(self eachElementDo: #cue &rest)
	)
)

(class oldSaltDice of DiceGame
	(properties
		diceScore 0
		highPair 0
	)
	
	(method (dScore &tmp temp0 temp1 theHighPair theHighPair_2)
		(= highPair 0)
		(Sort self reverseLT)
		(= temp0 (Count self diceCount (self at: 2)))
		(= theHighPair (+ ((self at: 2) cel?) 1))
		(switch temp0
			(5
				(= diceScore (+ 500 (* 10 theHighPair)))
			)
			(4
				(= diceScore (+ 400 (* 10 theHighPair)))
			)
			(else 
				(= temp0 (Count self diceCount (self at: 1)))
				(= temp1 (Count self diceCount (self at: 3)))
				(= theHighPair (+ ((self at: 1) cel?) 1))
				(= theHighPair_2 (+ ((self at: 3) cel?) 1))
				(switch temp0
					(3
						(if (== temp1 2)
							(= diceScore (+ 300 (* 10 theHighPair) theHighPair_2))
						else
							(= diceScore (+ 200 (* 10 theHighPair)))
						)
					)
					(2
						(cond 
							((== temp1 3) (= diceScore (+ 300 (* 10 theHighPair_2) theHighPair)))
							((== temp1 2)
								(= diceScore (+ 100 (* 10 theHighPair) theHighPair_2))
								(= highPair theHighPair)
							)
							(else
								(= diceScore (* 10 theHighPair))
								(= highPair theHighPair)
							)
						)
					)
					(else 
						(cond 
							((== temp1 3) (= diceScore (+ 200 (* 10 theHighPair_2))))
							((== temp1 2)
								(= diceScore (* 10 theHighPair_2))
								(= highPair theHighPair_2)
							)
							(else (= diceScore 0))
						)
					)
				)
			)
		)
	)
)

(instance reverseLT of Code

	(method (doit param1 param2)
		(return (> (param1 cel?) (param2 cel?)))
	)
)

(instance diceCount of Code

	(method (doit param1 param2)
		(return (== (param1 cel?) (param2 cel?)))
	)
)

(class CursorCoords of Object
	(properties
		cursorX 0
		cursorY 0
	)
)

(class InputList of Set
	
	(method (handleEvent event &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 eventX eventY temp12 temp13)
		(if (== (event type?) direction)
			(= temp7 [local7 (event message?)])
			(= temp3 60)
			(= temp4 400)
			(= eventX (event x?))
			(= eventY (event y?))
			(= temp5 (= temp2 0))
			(while (< temp5 size)
				(= temp12 ((= temp1 (self at: temp5)) cursorX?))
				(= temp13 (temp1 cursorY?))
				(if (or (!= eventX temp12) (!= eventY temp13))
					(= temp8 (GetAngle eventX eventY temp12 temp13))
					(= temp0 (GetDistance eventX eventY temp12 temp13))
					(if (> (= temp9 (Abs (- temp7 temp8))) 180)
						(= temp9 (- 360 temp9))
					)
					(= temp6 (<= temp9 (+ temp3 10)))
					(if
						(or
							(<= temp9 (- temp3 10))
							(and temp6 (< (+ temp9 temp0) (+ temp3 temp4)))
							(and
								temp6
								(== (+ temp9 temp0) (+ temp3 temp4))
								(< temp9 temp3)
							)
						)
						(= temp3 temp9)
						(= temp4 temp0)
						(= temp2 temp1)
					)
				)
				(++ temp5)
			)
			(if temp2
				(SetCursor
					theCursor
					1
					(temp2 cursorX?)
					(temp2 cursorY?)
				)
			)
		)
	)
	
	(method (empty &tmp temp0)
		(if size
			(= temp0 (- size 1))
			(while (>= temp0 0)
				((self at: temp0) dispose:)
				(self delete: (self at: temp0))
				(-- temp0)
			)
		)
	)
)

(instance diceRm of Room
	(properties
		picture 39
	)
	
	(method (init)
		(super init:)
		(LoadMany SCRIPT 375 376 377 378 379 380 381 382)
		(LoadMany VIEW 39 239 439 539 139)
		(LoadMany SOUND 76 77 75)
		(= useSortedFeatures FALSE)
		(self setRegions: 314)
		(oldSaltDice
			view: 39
			loop: 0
			init: 30 20 50 20 70 20 90 20 110 20
		)
		(johnnyDice init: 210 20 230 20 250 20 270 20 290 20)
		(saltRArmP stopUpd: init:)
		(redTumble setCel: (redTumble lastCel:) init:)
		(redTable setCel: 0 init:)
		(greenTumble
			setCel: (greenTumble lastCel:)
			setPri: 9
			init:
		)
		(greenTable setCel: 0 setPri: 9 init:)
		(cigarSmoke init: hide:)
		(saltLArmP stopUpd: init:)
		(saltMouth setPri: 8 init: cel: 1)
		(saltEyes setPri: 9 init: cel: 1)
		(johnnyLArmP init:)
		(johnnyRArmP stopUpd: init:)
		(johnnyMouth setPri: 6 init: hide:)
		(johnnyEyes setPri: 7 init: cel: 1)
		(johnnyFingers init: hide:)
		(rumBottle init: stopUpd:)
		(addToPics
			add: johnnyPV oldSaltV
			eachElementDo: #init
			doit:
		)
		(oldSaltMoney init:)
		(johnnyMoney init:)
		(= local0 4)
		(= local1 0)
		(= local2 3)
		(= local3 0)
		(curRoom
			setScript: (if demoScripts (ScriptID 375 0) else (ScriptID 376 0))
		)
		(oldSaltDice eachElementDo: #mousable 0)
		(directionHandler addToFront: self)
		(objList init: add:)
		(localproc_1ae6 1)
		(globalSound stop:)
		(soundDice number: (SoundFX 76) init:)
		(if (= howFast 2)
			(saltLArmP setScript: smokeCigar)
			(saltEyes setScript: blinkerScript)
			(johnnyLArmP setScript: johnTwitch)
			(johnnyEyes setScript: (Clone blinkerScript))
		)
	)
	
	(method (doit)
		(switch local16
			(0)
			(1
				(self setScript: (ScriptID 375 0))
			)
			(2
				(self setScript: (ScriptID 377 0))
			)
			(3
				(self setScript: (ScriptID 378 0))
			)
			(4
				(johnnyRArmP setScript: (ScriptID 382 0))
			)
		)
		(= local16 0)
		(super doit:)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(johnnyDice dispose:)
		(oldSaltDice dispose:)
		(objList dispose:)
		(cls)
		(= useSortedFeatures 1)
		(soundDice dispose:)
		(LoadMany 0 375 376 377 378 379 380 381 382)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'cease,stand[<up]') 
				(SetCursor theCursor TRUE 310 180)
				(curRoom newRoom: 32)
			)
			((Said 'bet/bottle')
				(if (ego has: iRumBottle)
					(Print 39 3)
				else
					(Print 39 4)
				)
			)
			((Said 'drink/rum')
				(if (ego has: iRumBottle)
					(Print 39 5)
				else
					(Print 39 4)
				)
			)
			((Said 'look>')
				(cond 
					((Said '[<around][/room]')
						(Print 39 6)
						(Print 39 7)
					)
					((Said '/table')
						(Print 39 8)
					)
				)
			)
			((Said 'address')
				(Print 39 9)
			)
			((Said 'count/money')
				(Print 39 10)
			)
		)
	)
	
	(method (notify param1 param2)
		(return
			(switch param1
				(0
					(if (< 1 argc) (= local4 param2) else (return local4))
				)
				(1
					(if (< 1 argc) (= local3 param2) else (return local3))
				)
				(2
					(if (< 1 argc) (= local1 param2) else (return local1))
				)
				(3
					(if (< 1 argc) (= local2 param2) else (return local2))
				)
				(4
					(if (< 1 argc) (= local0 param2) else (return local0))
				)
				(5
					(if (< 1 argc) (= local5 param2) else (return local5))
				)
				(6
					(if (< 1 argc) (= local17 param2) else (return local17))
				)
				(7
					(if (< 1 argc) (= local18 param2) else (return local18))
				)
				(8
					(if (< 1 argc) (= local19 param2) else (return local19))
				)
				(9
					(if (< 1 argc) (= local20 param2) else (return local20))
				)
				(10 (= local16 1))
				(11 (= local16 2))
				(12 (= local16 3))
				(13 (= local16 4))
			)
		)
	)
	
	(method (replay)
		;EO: Commenting out the code, since, while it's
		; a good anti-cheat measure, it's also an unfair
		; no-win situation.
;;;		(if (< 2 (++ numReplays))
;;;			(Print 39 0)
;;;			(if (not (ego has: iRumBottle))
;;;				((inventory at: iRumBottle) owner: curRoomNum)
;;;			)
;;;			(SetCursor theCursor TRUE 310 180)
;;;			(curRoom newRoom: 32)
;;;		else
;;;			(switch numReplays
;;;				(1 (Print 39 1))
;;;				(2 (Print 39 2))
;;;			)
;;;		)
	)
)

(instance blinkerScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 40 100)))
			(1
				(client cel: 0)
				(= cycles 2)
			)
			(2
				(client cel: 1)
				(= cycles 2)
			)
			(3 (self init:))
		)
	)
)

(instance smokeCigar of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 70 150)))
			(1
				(if local18
					(self init:)
				else
					(= register 1)
					(saltLArmP setLoop: 5 setCycle: EndLoop self)
				)
			)
			(2 (= cycles 10))
			(3
				(saltLArmP setCycle: BegLoop self)
			)
			(4
				(= register 0)
				(saltMouth loop: 9 cel: 0)
				(cigarSmoke show: setCycle: EndLoop self)
			)
			(5
				(saltMouth loop: 1 cel: 1 setCycle: 0)
				(cigarSmoke hide:)
				(self init:)
			)
		)
	)
)

(instance johnTwitch of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 50 100)))
			(1
				(cond 
					(local17 (self init:))
					((Random 0 1)
						(= register 1)
						(johnnyLArmP loop: 4 setCycle: ForwardCounter 3 self)
					)
					(else
						(= register 2)
						(johnnyFingers show: setCycle: ForwardCounter 3 self)
					)
				)
			)
			(2
				(switch register
					(1 (johnnyLArmP loop: 5 cel: 0))
					(2 (johnnyFingers hide:))
				)
				(= register 0)
				(self init:)
			)
		)
	)
)

(instance johnnyDice of oldSaltDice
	(properties
		view 39
		loop 1
	)
)

(instance oldSaltV of View
	(properties
		y 162
		x 49
		view 239
	)
	
	(method (init)
		(super init:)
		(self setPri: 7)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[/man]')
				(Print 39 11)
			)
			((Said 'kiss/man')
				(Print 39 12)
			)
		)
	)
)

(instance johnnyPV of PicView
	(properties
		y 176
		x 261
		view 39
		loop 3
		priority 5
	)
)

(instance cButton of Prop
	(properties
		y 36
		x 230
		view 439
		loop 7
		priority 5
	)
)

(instance rButton of Prop
	(properties
		y 36
		x 250
		view 439
		loop 8
		priority 5
	)
)

(instance nButton of Prop
	(properties
		y 36
		x 270
		view 439
		loop 9
		priority 5
	)
)

(instance oldSaltMoney of View
	(properties
		y 122
		x 139
		view 539
		loop 6
		cel 3
	)
)

(instance johnnyMoney of View
	(properties
		y 168
		x 198
		view 539
		loop 7
		cel 3
	)
)

(instance rumBottle of View
	(properties
		y 168
		x 150
		view 439
		loop 6
		priority 13
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/bottle]')
				(Print 39 13)
			)
			((Said 'get[/bottle]')
				(if (ego has: iRumBottle)
					(AlreadyTook)
				else
					(Print 39 14)
				)
			)
		)
	)
)

(instance johnnyLArmP of Prop
	(properties
		y 93
		x 274
		view 39
		loop 5
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance johnnyRArmP of Prop
	(properties
		y 87
		x 227
		view 39
		loop 7
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd:)
	)
)

(instance saltLArmP of Prop
	(properties
		y 113
		x 98
		view 239
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 10 stopUpd:)
	)
)

(instance saltRArmP of Prop
	(properties
		y 95
		x 45
		view 239
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd:)
	)
)

(instance redTumble of Prop
	(properties
		y 125
		x 120
		view 539
		loop 5
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance redTable of View
	(properties
		y 125
		x 120
		view 539
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance greenTumble of Prop
	(properties
		y 143
		x 213
		view 439
		loop 5
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance greenTable of View
	(properties
		y 143
		x 213
		view 439
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance cigarSmoke of Prop
	(properties
		y 82
		x 91
		view 239
		loop 7
	)
)

(instance johnnyFingers of Prop
	(properties
		y 144
		x 235
		view 39
		loop 8
	)
)

(instance saltMouth of Prop
	(properties
		y 79
		x 85
		view 139
		loop 1
	)
)

(instance saltEyes of Prop
	(properties
		y 74
		x 76
		view 139
		loop 4
	)
)

(instance johnnyMouth of Prop
	(properties
		y 78
		x 235
		view 139
		loop 6
	)
)

(instance johnnyEyes of Prop
	(properties
		y 69
		x 246
		view 139
		loop 5
	)
)

(instance soundDice of Sound
	(properties
		number 76
		priority 2
	)
)

(instance objList of InputList)
