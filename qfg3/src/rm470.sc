;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include game.sh)
(use Main)
(use TellerIcon)
(use bridgeIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm470 0
	proc470_1 1
	climbDown 2
	climbRope 3
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	theArcadeDifficulty
	local8
	local9 = [0 0 0 0 0 0 4 1 14 1 7 8 0 11 0 19 5 25]
	local29 = [0 9 20 -2 -3 -1 42 999]
	[local37 3]
	local40 = [0 5 6 7 8 56 55 999]
	local48 = [0 21 54 53 999]
	local53 = [0 18 24 17 15 51 52 999]
	[local61 2]
)
(procedure (proc470_1)
	(rope approachVerbs: 4)
	(rope2 approachVerbs: 4)
	(bridge approachVerbs: 4)
	(walkHandler delete: curRoom)
)

(instance rm470 of Room
	(properties
		noun 3
		picture 470
		east 420
		vanishingY -300
	)
	
	(method (init)
		(HandsOff)
		(Load RES_MESSAGE 470)
		(LoadMany 128 470 39 7 12)
		(cond 
			((== origHeroType THIEF) (Load RES_VIEW 30))
			((== prevRoomNum 460) (LoadMany RES_VIEW 475 472 473))
			(else (LoadMany RES_VIEW 475 972 971 471 474))
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 6 139 71 139 74 146 8 146
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 257 121 278 121 307 145 249 148 234 132
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 0 319 0 319 96 0 96
					yourself:
				)
		)
		(= [local37 0] @local29)
		(= [local37 1] 999)
		(egoActions init: ego @local29 @local37)
		(rope approachVerbs: 4 init:)
		(rope2 approachVerbs: 4 init:)
		(bridge approachVerbs: 4 init:)
		(wall init:)
		(bridge init:)
		(rock init:)
		(leftTreeTop init:)
		(rightTreeTop init:)
		((ScriptID 34 0) x: 200 textX: -175 talkWidth: 135)
		(cond 
			((== prevRoomNum 460) (curRoom setScript: initContest))
			((== prevRoomNum 480) (curRoom setScript: enterRoomL))
			(else (curRoom setScript: enterRoomB))
		)
		(super init:)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
				(and
					(ego mover?)
					(not ((ScriptID 34 1) script?))
					(== (ego view?) 12)
					(< (ego y?) 90)
				)
				(egoActions doVerb: 3)
			)
			((>= (ego x?) 315) (curRoom setScript: exitTo420))
			((>= (ego y?) 183) (curRoom setScript: walkOut))
			(
				(and
					(not (Btst 59))
					(cast contains: (ScriptID 34 1))
					local1
				)
				(Bset 59)
				(= local1 0)
				(messager say: 2 6 10)
			)
			((<= (ego x?) 5) (curRoom setScript: exitTo480))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(if (!= (cSound number?) 160)
			(cSound setLoop: -1 number: 160 play: 127)
		)
		(ego setScript: 0)
		(LoadMany 0 34 39 56 53 471)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (< (ego y?) 90)
					(egoActions doVerb: 3)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (notify param1 param2 &tmp temp0)
		(switch param1
			(1
				(if (> argc 1) (= local6 param2))
				(= temp0 local6)
			)
			(3
				(if (> argc 1)
					(= local0 param2)
					(switch param2
						(3
							(= [local61 0] @local48)
							(uhuraTeller init: (ScriptID 34 1) @local48 @local61)
						)
						(4
							(= [local61 0] @local53)
							(uhuraTeller init: (ScriptID 34 1) @local53 @local61)
						)
						(10 ((ScriptID 34 1) dispose:))
					)
				)
				(= temp0 local0)
			)
		)
		(return temp0)
	)
)

(instance egoActions of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-2
				(if (and (cast contains: (ScriptID 34 1)) local6)
					(not local4)
				else
					0
				)
				-3
				(if (and (cast contains: (ScriptID 34 1)) local6)
					(not local4)
				else
					0
				)
				-1
				(cast contains: (ScriptID 34 1))
				20
				(if (== local0 3)
					(cast contains: (ScriptID 34 1))
				else
					0
				)
				42
				(if (== local0 4)
					(cast contains: (ScriptID 34 1))
				else
					0
				)
				9
				(if (or (== local0 2) (== local0 1))
					(cast contains: (ScriptID 34 1))
				else
					0
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-2
					(= local4 1)
					(proc471_6 0 (= theArcadeDifficulty arcadeDifficulty))
					(curRoom setScript: 0)
					((ScriptID 34 1) setScript: (ScriptID 471 3))
					(return 0)
				)
				(-3
					(curRoom setScript: uhuraLeave)
					(return 0)
				)
				(-1
					(curRoom setScript: uhuraLeave)
					(return 0)
				)
			)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2 (super doVerb: theVerb))
			(3
				(curRoom setScript: walkBridge)
				((User curEvent?) claimed: 1)
			)
			(else  (ego doVerb: theVerb))
		)
	)
)

(instance uhuraTeller of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: 56 (== local0 1) 55 (== local0 2))
	)
)

(instance uhuraInstruct of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 152)
				(HandsOff)
				(ego setMotion: PolyPath 160 160 self)
			)
			(1
				(ego setHeading: 0)
				((ScriptID 34 1) setMotion: PolyPath 287 144 self)
			)
			(2
				((ScriptID 34 1)
					view: 471
					setLoop: 0
					cel: 0
					setPri: 10
					setCycle: Forward
					setScale:
					scaleX: 109
					scaleY: 109
					setStep: 3 3
					setMotion: MoveTo 287 132 self
				)
			)
			(3
				((ScriptID 34 1)
					setLoop: 2
					cel: 0
					x: 287
					y: 105
					setCycle: EndLoop self
				)
			)
			(4
				((ScriptID 34 1)
					setLoop: 3
					cel: 0
					x: 292
					y: 76
					setCycle: EndLoop self
				)
			)
			(5
				(messager say: 2 6 12 0 self)
			)
			(6
				((ScriptID 34 1)
					setStep: 3 2
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 160 ((ScriptID 34 1) y?) self
				)
			)
			(7
				(messager say: 2 6 13 0 self)
			)
			(8 (= ticks 30))
			(9
				(proc471_6 0 (= theArcadeDifficulty arcadeDifficulty))
				(self setScript: (ScriptID 471 5) self 1)
			)
			(10 (= ticks 30))
			(11
				(messager say: 2 6 15 0 self)
			)
			(12 (= ticks 30))
			(13
				(self setScript: (ScriptID 471 5) self 2)
			)
			(14 (= ticks 30))
			(15
				(messager say: 2 6 16 0 self)
			)
			(16 (= ticks 30))
			(17
				(self setScript: (ScriptID 471 5) self 4)
			)
			(18 (= ticks 30))
			(19
				(messager say: 2 6 17 0 self)
			)
			(20
				(messager say: 2 6 18 0 self)
			)
			(21
				((ScriptID 34 1) view: 471 setLoop: 4 cel: 0)
				(= local5 0)
				(= cycles 1)
			)
			(22
				((ScriptID 34 1)
					cel: (++ local5)
					x: (+ ((ScriptID 34 1) x?) [local9 (* local5 2)])
					y: (+ ((ScriptID 34 1) y?) [local9 (+ (* local5 2) 1)])
				)
				(= ticks 6)
			)
			(23
				(if (== local5 8)
					(= cycles 1)
				else
					(self changeState: (-- state))
				)
			)
			(24
				(messager say: 2 6 19 0 self)
			)
			(25
				((ScriptID 34 1)
					view: 971
					setLoop: -1
					setScale: 200
					setCycle: StopWalk 969
					setMotion: PolyPath ((ScriptID 34 1) x?) 250 self
				)
			)
			(26
				(= local0 10)
				(HandsOn)
				((ScriptID 34 1) dispose:)
			)
		)
	)
)

(instance uhuraLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 1 5 1 0 self)
			)
			(1
				((ScriptID 34 1)
					setMotion: PolyPath ((ScriptID 34 1) x?) 250 self
				)
			)
			(2
				(if (!= (cSound number?) 160) (cSound changeTo: 160))
				((ScriptID 34 1) dispose:)
				(HandsOn)
				(= local0 10)
				(if (and (Btst 152) (<= (ego y?) 90)) (proc471_7))
				(self dispose:)
			)
		)
	)
)

(instance enterRoomL of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setScale: 200
					x: -10
					y: 170
					actions: egoActions
					noun: 1
					normalize:
					init:
					setMotion: PolyPath 50 170 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterRoomB of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setScale: 200
					x: 160
					y: 210
					actions: egoActions
					noun: 1
					normalize:
					init:
					setMotion: PolyPath 160 175 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance exitTo480 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (- (ego x?) 30) (ego y?) self)
			)
			(1 (curRoom newRoom: 480))
		)
	)
)

(instance exitTo420 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 30) (ego y?) self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) 210 self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance uhuraEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					(
					(and local1 (== origHeroType 0) (not (Btst 59)))
						(= local1 0)
						(= local0 2)
						(messager say: 2 6 10 0 self)
						(= local2 0)
					)
					((== local0 1) (messager say: 2 6 4 0 self) (= local2 1))
					(else (= ticks 1))
				)
			)
			(1
				(cSound changeTo: 460)
				(= [local61 0] @local40)
				(uhuraTeller init: (ScriptID 34 1) @local40 @local61)
				((ScriptID 34 1)
					x: 180
					y: 250
					actions: uhuraTeller
					setScale: 200
					noun: 2
					init:
					setCycle: StopWalk 969
					setMotion: PolyPath 190 180 self
				)
			)
			(2
				(if (< (ego y?) 90)
					(messager say: 2 6 11 0 self)
				else
					(= ticks 1)
				)
			)
			(3
				(if local2 (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance uhuraEnterC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 460)
				(= cycles 3)
			)
			(1
				(HandsOff)
				(= local6 1)
				(messager
					say: 2 6 (if (== local0 3) 20 else 42) 0 self 470
				)
			)
			(2
				((ScriptID 34 1)
					x: 180
					y: 250
					setLoop: -1
					show:
					setCycle: StopWalk 969
					setMotion: PolyPath 180 170 self
				)
			)
			(3 (DontMove) (self dispose:))
		)
	)
)

(instance walkBridge of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch theArcadeDifficulty
					(1 (= temp0 150))
					(2 (= temp0 175))
					(else  (= temp0 200))
				)
				(= local8 (- 285 (ego x?)))
				(ego useSkill: 2 100)
				(if (> (ego trySkill: 2 temp0) 0)
					(= temp1 local8)
					(= local1 (if (>= [egoStats 2] temp0) 1 else -1))
				else
					(= local1 0)
					(= temp1
						(-
							(= temp1
								(/
									(* (- local8 50) (+ 100 (- [egoStats 2] temp0)))
									100
								)
							)
							(Random 1 20)
						)
					)
				)
				(if (<= temp1 10) (= temp1 (Random 1 20)))
				(ego
					setSpeed: 6
					setMotion: MoveTo (+ (ego x?) temp1) 80 self
				)
			)
			(1
				(if local1
					(self setScript: climbDown self)
				else
					(self setScript: (ScriptID 471 2) self)
				)
			)
			(2 (proc470_1) (self dispose:))
		)
	)
)

(instance climbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 0 setCycle: Walk setMotion: MoveTo 288 80 self)
			)
			(1
				(if (== local1 -1) (= local1 0))
				(= local3 0)
				(if
				(and (== origHeroType 0) (not (Btst 59)) local1)
					(Bset 59)
					(= local0 2)
					(if (cast contains: (ScriptID 34 1))
						(= local3 1)
						(messager say: 2 6 10 0 self)
					else
						(self setScript: uhuraEnter self)
						(Bset 59)
					)
					(= local1 0)
				else
					(= cycles 1)
				)
			)
			(2
				(if local3
					(= local3 0)
					(messager say: 2 6 11 0 self)
				else
					(= ticks 1)
				)
			)
			(3
				(ego
					view: 7
					setLoop: 4
					setPri: 10
					setCel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(4
				(ego
					y: (+ (ego y?) 57)
					x: (- (ego x?) 5)
					setLoop: 1
					setCel: (ego lastCel:)
					setCycle: CycleTo 0 -1 self
				)
			)
			(5
				(ego
					y: (+ (ego y?) 14)
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(6
				(ego
					view: 7
					y: (- (ego y?) 3)
					setLoop: 0
					setCel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(7
				(ego view: 0 setScale: 200 normalize:)
				(if (== local0 2)
					((ScriptID 34 1) setScript: uhuraInstruct)
				else
					(HandsOn)
				)
				(proc470_1)
				(self dispose:)
			)
		)
	)
)

(instance crossOverHand of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setSpeed: 6 setMotion: PolyPath 83 140 self)
			)
			(1
				(switch arcadeDifficulty
					(1 (= temp1 150))
					(2 (= temp1 180))
					(3 (= temp1 210))
				)
				(ego useSkill: 0 100)
				(if (> (ego trySkill: 0 temp1) 0)
					(= temp0 148)
				else
					(= temp0
						(/ (* 148 (+ 100 (- [egoStats 0] temp1))) 100)
					)
				)
				(if (<= temp0 20) (= temp0 (Random 10 30)))
				(ego
					view: 39
					setCycle: Forward
					y: 79
					setScale:
					scaleX: 111
					scaleY: 111
					setMotion: MoveTo (+ (ego x?) temp0) 79 self
				)
			)
			(2
				(ego view: 0 y: 141 setScale: 200 normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theArcadeDifficulty arcadeDifficulty)
				(rope approachVerbs: 0)
				(rope2 approachVerbs: 0)
				(bridge approachVerbs: 0)
				(ego
					setMotion: MoveTo (rope approachX?) (rope approachY?) self
				)
			)
			(1
				(ego
					view: 7
					setLoop: 0
					cel: 0
					setStep: 3 2
					setSpeed: 6
					yStep: 2
					xStep: 3
					setPri: 10
					ignoreActors: 1
					setScale:
					scaleX: 109
					scaleY: 109
					setCycle: EndLoop self
				)
			)
			(2
				(globalSound number: 928 setLoop: 1 play: 127)
				(ego setLoop: 1 y: 155 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego cel: 0 y: (- (ego y?) 14) setCycle: EndLoop self)
			)
			(4
				(ego cel: 0 y: (- (ego y?) 14) setCycle: CycleTo 5 1 self)
			)
			(5
				(ego
					y: (- (ego y?) 48)
					setLoop: 5
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego
					setLoop: 0
					view: 12
					setCycle: Walk
					setMotion: MoveTo 90 80 self
				)
			)
			(7
				(walkHandler addToFront: curRoom)
				(cond 
					((== local0 5) (ego setMotion: MoveTo 135 (ego y?) self))
					(local6 (ego setMotion: MoveTo 135 (ego y?) self))
					((and (!= local0 1) (Btst 59)) (self setScript: walkToCenter self))
					(else (= cycles 1))
				)
			)
			(8
				(if
					(or
						(cast contains: (ScriptID 39 1))
						(and
							(cast contains: (ScriptID 34 1))
							(< ((ScriptID 34 1) y?) 90)
						)
					)
					0
				else
					(HandsOn)
				)
				(if (== local0 5)
					((ScriptID 39 1) setScript: (ScriptID 471 4))
				)
				(self dispose:)
			)
		)
	)
)

(instance walkToCenter of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_13ea
			pushi    #setSpeed
			pushi    1
			pushi    6
			pushi    307
			pushi    4
			class    MoveTo
			push    
			pushi    100
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushSelf
			lag      ego
			send     18
			jmp      code_14e0
code_13ea:
			dup     
			ldi      1
			eq?     
			bnt      code_14e0
			pushi    2
			pushi    0
			lag      arcadeDifficulty
			sal      theArcadeDifficulty
			push    
			calle    proc471_6,  4
			pushi    140
			lsl      theArcadeDifficulty
			ldi      25
			mul     
			add     
			sat      temp0
			lsg      origHeroType
			ldi      2
			eq?     
			bnt      code_1421
			pushi    0
			calle    proc471_8,  0
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_14e0
code_1421:
			lal      local6
			not     
			bnt      code_14be
			lsl      local0
			ldi      10
			ne?     
			bnt      code_14be
			ldi      2
			lsgi     egoStats
			lat      temp0
			ge?     
			bnt      code_14be
			pushi    1
			pushi    153
			callb    Btst,  2
			not     
			bnt      code_14be
			pushi    1
			pushi    100
			callb    Btst,  2
			bnt      code_1453
			lsg      Day
			lag      wrestledDay
			eq?     
code_1453:
			not     
			bnt      code_14be
			pushi    #contains
			pushi    1
			pushi    2
			pushi    34
			pushi    1
			callk    ScriptID,  4
			push    
			lag      cast
			send     6
			not     
			bnt      code_14be
			pushi    1
			pushi    100
			callb    Btst,  2
			bnt      code_1479
			ldi      4
			sat      temp1
			jmp      code_147d
code_1479:
			ldi      3
			sat      temp1
code_147d:
			pushi    #notify
			pushi    2
			pushi    3
			lst      temp1
			lag      curRoom
			send     8
			pushi    #x
			pushi    1
			pushi    180
			pushi    0
			pushi    1
			pushi    250
			pushi    316
			pushi    1
			pushi    200
			pushi    214
			pushi    1
			pushi    2
			pushi    110
			pushi    0
			pushi    102
			pushi    0
			pushi    146
			pushi    1
			lofsa    uhuraEnterC
			push    
			pushi    2
			pushi    34
			pushi    1
			callk    ScriptID,  4
			send     38
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_14e0
code_14be:
			pushi    1
			pushi    152
			callb    Btst,  2
			bnt      code_14d7
			pushi    #dispose
			pushi    0
			self     4
			pushi    0
			calle    proc471_7,  0
			jmp      code_14e0
code_14d7:
			pushi    0
			callb    HandsOn,  0
			pushi    #dispose
			pushi    0
			self     4
code_14e0:
			toss    
			ret     
		)
	)
)

(instance initContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 5)
				(ego
					normalize:
					setSpeed: 6
					setScale: 200
					setHeading: 180
					x: 30
					y: 149
					init:
				)
				((ScriptID 39 1)
					setScale: 200
					setHeading: 180
					x: 287
					y: 144
					ignoreActors: 1
					init:
				)
				(if (== (cSound number?) 462)
					(cSound number: 490 setLoop: -1 play:)
				else
					(cSound setLoop: -1 changeTo: 490)
				)
				(= cycles 1)
			)
			(1
				(messager say: 8 6 47 0 self)
			)
			(2
				(ego
					view: 7
					setLoop: 0
					cel: 0
					setPri: 10
					ignoreActors: 1
					setScale:
					scaleX: 109
					scaleY: 109
					setCycle: EndLoop self
				)
				((ScriptID 39 1) setHeading: 0)
			)
			(3
				(ego setLoop: 1 y: 155 cel: 0 setCycle: EndLoop self)
				((ScriptID 39 1)
					view: 472
					setLoop: 3
					setPri: 11
					setScale:
					scaleX: 109
					scaleY: 109
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4)
			(5
				(ego cel: 0 y: (- (ego y?) 14) setCycle: EndLoop self)
				((ScriptID 39 1)
					setLoop: 0
					cel: 0
					x: 281
					y: 143
					setCycle: Forward
					setStep: 3 3
					setMotion: MoveTo 289 119 self
				)
			)
			(6
				(ego cel: 0 y: (- (ego y?) 14) setCycle: CycleTo 4 1 self)
			)
			(7)
			(8
				(ego
					y: (- (ego y?) 48)
					x: (- (ego x?) 6)
					setLoop: 5
					cel: 0
					setCycle: EndLoop self
				)
				((ScriptID 39 1)
					setLoop: 2
					cel: 0
					x: 293
					y: 79
					setPri: 10
					setStep: 4 2
					setCycle: EndLoop self
				)
			)
			(9)
			(10
				(ego
					view: 0
					setCycle: StopWalk 5
					setMotion: MoveTo 30 80 self
				)
			)
			(11
				(ego
					view: 12
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 135 (ego y?) self
				)
				((ScriptID 39 1)
					setLoop: 1
					cel: 0
					x: 285
					y: 76
					setCycle: Walk
					setMotion: MoveTo 160 76 self
				)
			)
			(12)
			(13
				(messager say: 8 6 48 0 self)
			)
			(14
				(proc471_6 1 (= theArcadeDifficulty arcadeDifficulty))
				(client setScript: (ScriptID 471 4))
			)
		)
	)
)

(instance rock of Feature
	(properties
		x 53
		y 175
		noun 7
		nsTop 167
		nsLeft 36
		nsBottom 183
		nsRight 71
		sightAngle 180
	)
)

(instance leftTreeTop of Feature
	(properties
		x 38
		y 26
		noun 9
		nsTop 17
		nsLeft 8
		nsBottom 35
		nsRight 68
		sightAngle 180
	)
)

(instance rightTreeTop of Feature
	(properties
		x 218
		y 25
		noun 10
		nsTop 16
		nsLeft 166
		nsBottom 35
		nsRight 271
		sightAngle 180
	)
)

(instance bridge of Feature
	(properties
		x 154
		y 76
		noun 4
		nsTop 68
		nsLeft 12
		nsBottom 82
		nsRight 311
		sightAngle 180
		approachX 83
		approachY 140
	)
	
	(method (doVerb theVerb)
		(cond 
			((!= theVerb 4) (super doVerb: theVerb &rest))
			((curRoom script?) 0)
			((<= (ego y?) 90) (curRoom doVerb: 3))
			((== local0 1) (messager say: 2 6 57))
			(else (curRoom setScript: crossOverHand))
		)
	)
)

(instance rope of Feature
	(properties
		x 35
		y 104
		noun 5
		nsTop 68
		nsLeft 11
		nsBottom 158
		nsRight 52
		sightAngle 180
		approachX 30
		approachY 143
	)
	
	(method (doVerb theVerb)
		(cond 
			((!= theVerb 4) (super doVerb: theVerb &rest))
			((or (curRoom script?) (<= (ego y?) 90)) (bridge doVerb: &rest))
			((not (Btst 61)) (Bset 61) (= local0 1) (curRoom setScript: uhuraEnter))
			(else (curRoom setScript: climbRope))
		)
	)
)

(instance rope2 of Feature
	(properties
		x 282
		y 97
		nsTop 82
		nsLeft 246
		nsBottom 166
		nsRight 301
		sightAngle 180
		approachX 30
		approachY 143
	)
	
	(method (doVerb theVerb)
		(rope doVerb: theVerb &rest)
	)
)

(instance wall of Feature
	(properties
		x 159
		y 65
		noun 6
		nsTop 39
		nsBottom 92
		nsRight 319
		sightAngle 40
		approachX 159
		approachY 65
	)
)
