;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh)
(use Main)
(use Procs)
(use Tactor)
(use TScripts)
(use Intrface)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)

(public
	Room355 0
)

(local
	[str 201]
	local201
	local202 =  -1
)
(instance Room355 of FRoom
	(properties
		lookStr {You are now inside Bookend's cave.}
		picture 355
		style $0000
		west 350
		westX 195
		westY 172
		defeatEntrance 1
	)
	
	(method (init)
		(= gameState 2)
		(LoadMany SOUND 47 141 142 143 144 108)
		(LoadMany VIEW
			(+ 30 egoView)
			751
			754
			755
			756
			760
			762
			763
			764
			747
		)
		(LoadMany SCRIPT JUMP)
		(dragon init: view: 755 setLoop: 0 setCel: 0)
		(dragonHead init: setLoop: 1 setCel: 1)
		(dragonClaw init: setLoop: 4 setCycle: Forward)
		(super init:)
		(theGame setScript: theZapCursor)
		(if (theGame script?)
			(theGame setScript: 0)
			(theMusic2 stop:)
		)
		(theMusic number: 40 setLoop: -1 play:)
		(theIconBar enable: show:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						117 100
						121 97
						132 95
						137 92
						168 93
						190 95
						181 104
						168 110
						140 111
						134 107
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 -26
						318 -26
						319 173
						0 173
						0 150
						251 155
						227 118
						235 99
						223 83
						214 81
						193 79
						174 81
						163 79
						149 76
						147 76
						132 75
						122 76
						124 70
						123 64
						100 64
						92 70
						93 76
						85 83
						0 85
					yourself:
				)
		)
		(rocks init:)
		(fire setCycle: Forward init:)
		(self setScript: endIt)
	)
	
	(method (dispose)
		(LoadMany FALSE JUMP)
		(if (theMusic handle?)
			(theMusic fade: 0 15 12 1)
		)
		(super dispose: &rest)
	)
	
	(method (enterSpecial)
		(NormalEgo 0 egoView)
		(ego
			posn: 200 142
			normal: 1
			setCycle: StopWalk
			ignoreActors: 1
			init:
		)
		(theBookEnd
			view: 760
			setLoop: 0
			setCel: 0
			posn: 197 103
			init:
		)
		(bookWave init: setCycle: armOsc)
		(bookArm init: setCycle: armOsc2)
		(bookMouth init: setCycle: mouthOsc)
	)
)

(instance endIt of HandsOffScript
	
	(method (doit)
		(super doit: &rest)
		(if (and local201 (== (theMusic prevSignal?) local202))
			(= local201 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setScript: theZapCursor)
				(ego setHeading: 270)
				(= local202 10)
				(theMusic number: 47 setLoop: 1 play:)
				(= ticks 1)
			)
			(1
				(dragonHead setCycle: CycleTo 4 1 self)
			)
			(2
				(dragonRoar play:)
				(dragonHead setCycle: CycleTo 10 1 self)
				(fire setLoop: 2)
			)
			(3
				(dragonHead setCycle: CycleTo 1 -1 self)
			)
			(4
				(dragonHead setCycle: dragonBreathe 1 self)
			)
			(5
				(fire setLoop: 0)
				(dragonHead setCel: 0)
				(dragonEyeMouth init: setCel: 0 show:)
				(dragonEye init: setCel: 0 show: setCycle: EndLoop self)
				(dragonClaw setCel: 0 show: setCycle: Forward)
			)
			(6
				(dragonGrowl play:)
				(dragonEyeMouth setCycle: EndLoop)
				(= ticks 20)
			)
			(7
				(Print 355 0
					#at 70 150
					#color 32
					#time 5
					#dispose
					self
				)
			)
			(8
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance book of Actor
	(properties
		view 355
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance egoLeap of Prop
	(properties
		view 30
		loop 1
	)
)

(instance fire of Prop
	(properties
		x 158
		y 94
		description {Fire}
		lookStr {The fire burns brightly in this cave.}
		view 355
	)
)

(instance bookWyrm of Tactor
	(properties
		description {Bookwyrm}
		lookStr {Bookwyrm is here.}
		view 764
		talkerID 18
	)
	
	(method (setUp)
		((= talkerObj bookWyrmTalkObj)
			setUp: bookWyrmBust bookWyrmEyes bookWyrmMouth
		)
	)
)

(instance bookWyrmTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 749
	)
)

(instance bookWyrmBust of View
	(properties
		nsTop 25
		nsLeft 45
		view 749
		loop 6
	)
)

(instance bookWyrmEyes of Prop
	(properties
		nsTop 27
		nsLeft 46
		view 749
		loop 4
		cycleSpeed 60
	)
)

(instance bookWyrmMouth of Prop
	(properties
		nsTop 42
		nsLeft 43
		view 749
		loop 2
		cycleSpeed 12
	)
)

(instance rocks of Feature
	(properties
		x 183
		y 60
		description {Rocks}
		sightAngle 90
		onMeCheck cGREEN
		lookStr {The rocks are cold and wet to the touch.}
	)
)

(instance bookArm of Prop
	(properties
		x 203
		y 103
		z 21
		view 760
		loop 2
	)
)

(instance bookWave of Prop
	(properties
		x 181
		y 103
		z 21
		view 760
		loop 1
	)
)

(instance bookMouth of Prop
	(properties
		x 191
		y 103
		z 19
		view 760
		loop 3
	)
)

(instance dragon of Prop
	(properties
		x 102
		y 110
		view 754
		priority 6
		signal fixPriOn
	)
)

(instance dragonHead of Prop
	(properties
		x 84
		y 81
		view 755
		loop 1
		priority 8
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance dragonEyeMouth of Prop
	(properties
		x 98
		y 59
		view 755
		loop 2
		priority 9
		signal fixPriOn
		cycleSpeed 18
	)
)

(instance dragonEye of Prop
	(properties
		x 99
		y 50
		view 755
		loop 3
		priority 10
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance dragonClaw of Prop
	(properties
		x 73
		y 101
		view 755
		loop 4
		priority 9
		signal fixPriOn
		cycleSpeed 18
	)
)

(instance handShake of Oscillate
	
	(method (doit &tmp oscNextCel)
		(if
			(or
				(> (= oscNextCel (self nextCel:)) 6)
				(< oscNextCel 4)
			)
			(= cycleDir (- cycleDir))
			(self cycleDone:)
		else
			(client cel: oscNextCel)
		)
	)
)

(instance dragonBreathe of Oscillate
	
	(method (doit &tmp oscNextCel)
		(if
			(or
				(> (= oscNextCel (self nextCel:)) 4)
				(< oscNextCel 1)
			)
			(= cycleDir (- cycleDir))
			(self cycleDone:)
		else
			(client cel: oscNextCel)
		)
	)
)

(instance dragonBreath of Sound
	(properties
		flags mNOPAUSE
		number 141
	)
)

(instance dragonRoar of Sound
	(properties
		flags mNOPAUSE
		number 142
	)
)

(instance dragonFlames of Sound
	(properties
		flags mNOPAUSE
		number 143
	)
)

(instance dragonGrowl of Sound
	(properties
		flags mNOPAUSE
		number 144
	)
)

(instance egoIntoBook of Sound
	(properties
		flags mNOPAUSE
		number 108
	)
)

(class armOsc of Oscillate
	
	(method (init)
		(super init: &rest)
		(client cycleSpeed: (Random 100 200))
		(= cycleCnt (GetTime))
	)
	
	(method (nextCel &tmp temp0)
		(return
			(cond 
				(
					(<=
						(- (= temp0 (GetTime)) cycleCnt)
						(client cycleSpeed?)
					)
					(return (client cel?))
				)
				((and (== (client cel?) 1) (< cycleDir 0))
					(client cycleSpeed: (Random 100 200))
					(return 0)
				)
				((not (client cel?))
					(client cycleSpeed: 6)
					(return (= cycleDir 1))
				)
				(else
					(= cycleCnt (GetTime))
					(return (+ (client cel?) cycleDir))
				)
			)
		)
	)
)

(instance armOsc2 of armOsc)

(instance mouthOsc of armOsc)
