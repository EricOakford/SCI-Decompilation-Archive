;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOAT)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	moatRg 0
)

(local
	local0
	local1
	monsterLookLoop
	monsterLoop
	local4
)
(instance moatRg of Region
	(properties)
	
	(method (init)
		(LoadMany VIEW 14 107 108)
		(if (>= howFast 1)
			(LoadMany VIEW 80 75 76 81)
			(if (Btst fGoatFollows)
				(LoadMany VIEW 163 164)
			)
		)
		(self keep: FALSE)
		(super init:)
		(monsterHead1
			setPri: 4
			init:
			hide:
			setChecks: 1 8
			shiftClick: 1
			setScript: monsterLeftRight
		)
		(monsterTail1
			setPri: 4
			init:
			hide:
			setChecks: 1 8
			shiftClick: 1
		)
		(moatMonster
			setPri: 4
			init:
			hide:
			setChecks: 1 8
			shiftClick: 1
			setScript: monsterLookScript
		)
		(if (>= howFast 1)
			(egoReflect init:)
			(goatReflect init:)
		)
	)
	
	(method (doit)
		(if (!= (= local0 (ego onControl: origin)) local1)
			(= local1 local0)
			(switch local0
				(cRED
					(egoReflect dispose:)
					(moatMonster setScript: 0)
					(ego setScript: drownFrontScript)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room]')
						(Print 605 0)
					)
					((Said '/castle')
						(Print 605 1)
					)
					((Said '/moat')
						(Print 605 2)
					)
					((Said '/water')
						(Print 605 3)
					)
					((Said '/ivy,vine')
						(Print 605 4)
					)
				)
			)
			((Said 'take,get,take,pick/ivy,vine')
				(Print 605 5)
			)
			((Said 'pet,ride,capture/serpent,animal,animal')
				(Print 605 6)
			)
			((Said 'attack,kill,shoot/serpent,animal,animal')
				(Print 605 7)
			)
			((Said 'climb,scale[<in,in]/serpent,animal,animal')
				(Print 605 8)
			)
			((Said 'drink,get,take/water,drink')
				(if (and (ego has: iWaterBucket) (Btst fWaterInBucket))
					(event claimed: FALSE)
				else
					(Print 605 9)
				)
			)
			((Said 'smell,sniff[/moat,brook,water]')
				(Print 605 10)
			)
			((Said 'dive[<in,on,in][/moat]')
				(Print 605 11)
			)
			((Said 'climb,scale')
				(Print 605 12)
			)
			((or (Said 'go/swim') (Said 'swim') (Said 'enter,go/moat'))
				(Print 605 13)
			)
			((Said 'steal,pick,get,take/animal,serpent')
				(Print 605 14)
			)
			((Said 'talk,speak/animal,serpent')
				(Print 605 15)
			)
			(
			(or (Said 'get,take/water') (Said 'fill/bucket'))
				(cond 
					((Btst fWaterInBucket)
						(Print 605 16)
					)
					((not (ego has: iWaterBucket))
						(Print 605 17)
					)
					((& (ego onControl: origin) cLBLUE)
						(Print 605 18)
						(BucketState TRUE)
					)
					(else
						(Print 605 19)
					)
				)
			)
		)
	)
)

(instance drownFrontScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 14
					yStep: 5
					setLoop: 1
					setCel: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 25) self
				)
			)
			(1
				((ScriptID 0 21) number: 14 init: play:)
				(ego setCycle: EndLoop self)
			)
			(2
				(if (Btst fInvisible)
					(Print 605 20)
					(theGame changeScore: -3)
				)
				((ScriptID 0 21) number: 96 loop: -1 play:)
				(ego setLoop: 0 cycleSpeed: 0 setCycle: Forward)
				(= cycles 20)
			)
			(3
				(ego setCycle: CycleTo 5 1 self)
			)
			(4
				(if (< (ego x?) 160)
					(moatMonster view: 108 x: (- (ego x?) 18) setLoop: 1)
				else
					(moatMonster x: (+ (ego x?) 18) setLoop: 0)
				)
				(ego setCycle: EndLoop)
				(moatMonster
					y: (+ (ego y?) 2)
					view: 107
					cycleSpeed: 1
					setCycle: EndLoop self
					show:
				)
			)
			(5
				(ego hide:)
				(moatMonster
					setLoop: (+ 2 (< (ego x?) 160))
					setCycle: Forward
				)
				(= cycles 40)
			)
			(6
				(moatMonster
					setLoop: (+ 4 (< (ego x?) 160))
					setCycle: EndLoop self
				)
			)
			(7
				(moatMonster hide:)
				(= cycles 30)
			)
			(8
				((ScriptID 0 21) number: 50 loop: 1 play: self)
				(moatMonster
					show:
					view: 108
					setLoop: (+ 5 (> (ego x?) 160))
					setCel: 0
					setCycle: EndLoop
				)
				(= cycles 30)
			)
			(9
				(moatMonster setCycle: BegLoop)
			)
			(10
				(moatMonster dispose:)
				(EgoDead {The moat monsters appreciate your good taste.})
				(HandsOn)
			)
		)
	)
)

(instance monsterLeftRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= monsterLoop (Random 0 1))
				(switch curRoomNum
					(1
						(monsterHead1 x: (Random 60 260))
					)
					(2
						(monsterHead1 x: (Random 120 260))
					)
					(83
						(monsterHead1 x: (Random 60 200))
					)
				)
				(if (== curRoomNum 1)
					(monsterHead1 y: (Random 148 168))
				else
					(monsterHead1 y: (Random 169 186))
				)
				(= cycles
					(Random
						(/ 100 (+ howFast 1))
						(/ 600 (+ howFast 1))
					)
				)
			)
			(1
				(TempSound number: 97 loop: -1 init: play:)
				(monsterHead1
					show:
					setLoop: monsterLoop
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(monsterTail1
					x: (monsterHead1 x?)
					y: (monsterHead1 y?)
					cycleSpeed: 1
				)
				(monsterHead1
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(monsterHead1
					x: (+ (monsterHead1 x?) (- (* monsterLoop 120) 60))
					setLoop: monsterLoop
					setCel: 0
					setCycle: EndLoop
				)
				(monsterTail1
					show:
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(monsterHead1
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: Forward
				)
				(monsterTail1
					setLoop: (+ monsterLoop 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(monsterHead1 hide:)
				(monsterTail1
					x: (monsterHead1 x?)
					setLoop: (+ monsterLoop 2)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(monsterTail1
					setLoop: (+ monsterLoop 4)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(TempSound loop: 1 fade:)
				(monsterTail1 setCel: 0 hide:)
				(= cycles 1)
			)
			(8
				(self init:)
			)
		)
	)
)

(instance monsterLookScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles
					(Random
						(/ 100 (+ howFast 1))
						(/ 600 (+ howFast 1))
					)
				)
			)
			(1
				(if (== curRoomNum 1)
					(moatMonster y: (Random 148 168))
				else
					(moatMonster y: (Random 169 186))
				)
				(moatMonster
					x: (Random 50 270)
					show:
					setLoop: 2
					setCel: 0
					cycleSpeed: (Random 1 2)
					setCycle: EndLoop self
				)
			)
			(2
				(= monsterLookLoop (Random 0 1))
				(moatMonster setLoop: monsterLookLoop setCel: 0 setCycle: EndLoop self)
			)
			(3
				(if (= local4 (Random 0 1))
					(moatMonster
						setLoop: (+ monsterLookLoop 3)
						setCel: 0
						setCycle: EndLoop
					)
					(= cycles (Random 40 60))
				else
					(self cue:)
				)
			)
			(4
				(moatMonster setCycle: BegLoop self)
			)
			(5
				(if local4
					(moatMonster
						setLoop: monsterLookLoop
						setCel: (moatMonster lastCel:)
						setCycle: BegLoop self
					)
				else
					(self cue:)
				)
			)
			(6
				(moatMonster
					setLoop: 2
					setCel: (moatMonster lastCel:)
					setCycle: BegLoop self
				)
			)
			(7
				(moatMonster hide:)
				(= cycles 2)
			)
			(8
				(moatMonster stopUpd:)
				(self init:)
			)
		)
	)
)

(instance monsterHead1 of Prop
	(properties
		x 45
		y 145
		description {moat monster}
		view 107
		loop 1
		signal $0000
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/serpent,animal,animal')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 605 21)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance monsterTail1 of Prop
	(properties
		x 45
		y 145
		description {monster's tail}
		view 107
		loop 5
		signal $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 605 21)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance moatMonster of Prop
	(properties
		description {moat monster}
		view 108
		signal $0000
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/serpent,animal,animal')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 605 21)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance TempSound of Sound
	(properties
		priority 3
	)
)

(instance goatReflect of View
	(properties
		description {reflection}
		view 163
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst fGoatFollows)
			(self
				loop: (theGoat loop?)
				x: (theGoat x?)
				y: (+ (theGoat y?) 20)
				cel: (theGoat cel?)
			)
		)
	)
	
	(method (doit)
		(if (and (>= howFast 1) (Btst fGoatFollows))
			(if (== (theGoat view?) 165)
				(self view: 163)
			else
				(self view: 164)
			)
			(self
				loop: (theGoat loop?)
				x: (theGoat x?)
				cel: (theGoat cel?)
				y: (+ (theGoat y?) 20)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 605 22)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoReflect of View
	(properties
		description {reflection}
		closeRangeDist 500
		longRangeDist 500
		view 80
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(if (>= howFast 1)
			(self
				loop: (ego loop?)
				x: (ego x?)
				cel: (ego cel?)
				y: (+ (ego y?) 20)
			)
			(if (Btst fInvisible)
				(egoReflect hide:)
			)
		)
	)
	
	(method (doit param1)
		(asm
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_0f49
			pushi    1
			pushi    1
			callb    Btst,  2
			bnt      code_0ebd
			pushi    #hide
			pushi    0
			self     4
			jmp      code_0ec3
code_0ebd:
			pushi    #show
			pushi    0
			self     4
code_0ec3:
			pushi    5
			pushi    1
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0eda
			ldi      80
			jmp      code_0f15
code_0eda:
			dup     
			ldi      2
			eq?     
			bnt      code_0ee6
			ldi      81
			jmp      code_0f15
code_0ee6:
			dup     
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0ef4
			ldi      23
			;jmp      code_0ef6
code_0ef4:
			ldi      16
code_0ef6:
			eq?     
			bnt      code_0eff
			ldi      76
			jmp      code_0f15
code_0eff:
			dup     
			pushi    1
			pushi    0
			callb    Btst,  2
			bnt      code_0f0d
			ldi      17
			jmp      code_0f0f
code_0f0d:
			ldi      15
code_0f0f:
			eq?     
			bnt      code_0f15
			ldi      75
code_0f15:
			toss    
			push    
			pushi    6
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    7
			pushi    1
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
			pushi    4
			pushi    1
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    3
			pushi    1
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      20
			add     
			push    
			self     30
code_0f49:
			pushi    #doit
			pushi    0
			&rest    param1
			super    View,  4
			ret     
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/reflection')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 605 23)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
