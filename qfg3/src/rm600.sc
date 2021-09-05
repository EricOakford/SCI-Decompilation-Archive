;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh)
(use Main)
(use TellerIcon)
(use PChase)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0 = [0 7 -6 -14 -9 999]
	local6 = [0 2 11 12 16 17 999]
	[local13 6]
	[local19 5]
	local24
	local25
	local26 =  180
	[local27 2]
	local29
	local30
	local31
)
(procedure (localproc_008e)
	(return
		(if (< local26 126)
			(return 5)
		else
			(return (- 20 (/ (- 190 local26) 4)))
		)
	)
)

(instance rm600 of Room
	(properties
		noun 4
		picture 600
		vanishingY 75
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 1 319 2 319 151 247 137 229 139 208 130 152 129 140 127 0 120
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 16 140 77 149 154 174 154 189 0 189
					yourself:
				)
		)
		((ScriptID 36 0) x: 200 y: 2 textX: -175 textY: 150)
		(= [local13 0] @local0)
		(= [local19 0] @local6)
		(gate init:)
		(cSound number: 600 setLoop: -1 play: 127)
		(switch prevRoomNum
			(620
				(ego
					view: 0
					x: 309
					y: 175
					loop: 3
					cel: 0
					init:
					setScale: 195
				)
				(joHari
					x: 290
					y: 175
					signal: 16384
					noun: 1
					init:
					moveSpeed: (ego moveSpeed?)
					cycleSpeed: (ego cycleSpeed?)
					origStep: (ego origStep?)
					setScale: 195
					setCycle: Walk
				)
				(super init:)
				(curRoom setScript: eventThree)
			)
			(650
				(walkHandler addToFront: self)
				(egoTell init: ego @local0 @local13)
				(johariTell init: joHari @local6 @local19)
				(ego
					x: 175
					y: 185
					code: checkSouth
					noun: 2
					signal: 16384
					init:
					normalize: 3
					setScale: 195
				)
				(joHari
					x: 200
					y: 180
					noun: 1
					origStep: (ego origStep?)
					actions: johariTell
					init:
					moveSpeed: (ego moveSpeed?)
					cycleSpeed: (ego cycleSpeed?)
					setScale: 195
					setCycle: joHariStop 974
				)
				(super init:)
				(curRoom setScript: eventFour)
			)
			(else 
				(if (and (== prevRoomNum 170) (Btst 88))
					(Bclr 88)
					(walkHandler addToFront: self)
					(egoTell init: egoPic @local0 @local13)
					(johariTell init: johariPic @local6 @local19)
					(egoPic init:)
					(johariPic init:)
					(kissView init:)
					(ego view: 0 loop: 3 cel: 0 init: setScale: 195 hide:)
					(joHari
						signal: 16384
						init:
						moveSpeed: (ego moveSpeed?)
						cycleSpeed: (ego cycleSpeed?)
						setScale: 195
						setCycle: Walk
						hide:
					)
					(= local29 1)
					(super init:)
					(HandsOn)
					(curRoom setScript: eventOne)
				else
					(ego
						view: 0
						x: 190
						y: 180
						code: checkSouth
						loop: 3
						cel: 0
						noun: 2
						init:
						setStep: 2 2
						setScale: 195
					)
					(super init:)
					(curRoom setScript: eventFive)
				)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(DisposeScript 36)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((== prevRoomNum 650)
						(= local25 ((user curEvent?) x?))
						(= local26 ((user curEvent?) y?))
						(ego setMotion: PolyPath local25 local26 joHari)
						(joHari setMotion: PFollow ego 12)
					)
					(local24 (curRoom setScript: kissing))
					(else (messager say: 2 5 6 2) (= local24 1))
				)
			)
			(74
				(if (== prevRoomNum 650)
					(messager say: 3 6 25)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance eventOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1 (messager say: 1 2 1 0 self))
			(2
				(switch heroType
					(1
						(messager say: 1 2 10 0 self)
					)
					(2 (messager say: 1 2 3 0 self))
					(else 
						(if (ego has: 46)
							(messager say: 1 2 4 0 self)
						else
							(self dispose:)
						)
					)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance eventTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(kissView dispose:)
				(johariPic dispose:)
				(egoPic dispose:)
				(PalVary PALVARYSTART 600 0)
				(= seconds 2)
			)
			(1
				(DrawPic 600 FADEOUT)
				(gate show:)
				(= seconds 2)
			)
			(2
				(if (!= (PalVary PALVARYINFO) 64)
					(Bset 31)
					(Bset 81)
					(= Clock 3200)
				)
				(messager say: 1 2 8 0 self)
			)
			(3
				(ego
					x: 200
					y: 280
					show:
					setStep: 2 2
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 200 200 self
				)
				(joHari
					x: 170
					y: 280
					show:
					setCycle: Walk
					setMotion: MoveTo 170 200 self
				)
			)
			(4 0)
			(5
				(messager say: 1 2 15 0 self)
			)
			(6
				(ego setMotion: MoveTo (+ (ego x?) 90) (ego y?) self)
				(joHari
					setMotion: MoveTo (+ (joHari x?) 100) (joHari y?) self
				)
			)
			(7 0)
			(8 (curRoom newRoom: 620))
		)
	)
)

(instance eventThree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 170 (+ (gate y?) 30) self)
				(joHari setMotion: MoveTo 165 (+ (gate y?) 23) self)
			)
			(1 0)
			(2
				(messager say: 1 2 18 0 self)
			)
			(3
				(if (== heroType 1)
					(messager say: 1 6 2 0 self)
				else
					(self cue:)
				)
			)
			(4
				(if (ego has: 46)
					(messager say: 1 2 20 0 self)
				else
					(self cue:)
				)
			)
			(5
				(joHari setMotion: MoveTo 148 (+ (gate y?) 5) self)
				(ego setMotion: MoveTo 152 (+ (gate y?) 10) self)
			)
			(6 0)
			(7
				(messager say: 1 2 21 0 self)
			)
			(8
				(gate
					setMotion: MoveTo (- (gate x?) 15) (gate y?) self
				)
				(globalSound number: 602 play: 127)
			)
			(9
				(ego setMotion: MoveTo 147 (- (gate y?) 2) self)
				(joHari setMotion: MoveTo 143 (- (gate y?) 4) self)
			)
			(10 (curRoom newRoom: 620))
		)
	)
)

(instance eventFour of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(messager say: 1 6 22 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventFive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 147 (+ (gate y?) 5) self)
			)
			(1
				(messager say: 3 2 21 0 self)
			)
			(2
				(gate
					setMotion: MoveTo (- (gate x?) 15) (gate y?) self
				)
				(globalSound number: 602 play: 127)
			)
			(3
				(ego setMotion: MoveTo 145 120 self)
			)
			(4
				(if (and (== prevRoomNum 170) (ego has: 46))
					(curRoom newRoom: 620)
				else
					(curRoom newRoom: 650)
				)
			)
		)
	)
)

(instance kissing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(kissView setLoop: 0 setCycle: EndLoop self)
			)
			(1 (= seconds 3))
			(2
				(kissView
					cel: 0
					setLoop: 1
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(3
				(sFx number: 603 play:)
				(= seconds 2)
			)
			(4
				(ego solvePuzzle: 297 3)
				(DrawPic 0 FADEOUT)
				(cast eachElementDo: #hide)
				(= seconds 1)
			)
			(5
				(curRoom setScript: eventTwo)
			)
		)
	)
)

(instance toVillage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 200 210 self)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)

(instance toRm650 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego code: 0 setMotion: MoveTo 147 127 self)
				(joHari signal: 16384 setMotion: MoveTo 143 132 self)
			)
			(1 0)
			(2
				(gate setMotion: MoveTo 130 (gate y?) self)
				(globalSound number: 602 play:)
			)
			(3
				(ego setMotion: MoveTo 147 (- (gate y?) 3) self)
				(joHari setMotion: MoveTo 143 (- (gate y?) 2) self)
			)
			(4 0)
			(5 (curRoom newRoom: 650))
		)
	)
)

(instance toLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego code: 0)
				(messager say: 1 6 23 0 self)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)

(instance checkSouth of Code
	(properties)
	
	(method (doit)
		(cond 
			((> (ego y?) 185) (curRoom setScript: toLeave))
			((& (ego onControl:) $0008) (gate dispose:) (curRoom newRoom: 650))
		)
	)
)

(instance johariPic of Feature
	(properties
		x 50
		y 160
		noun 1
		onMeCheck $0002
	)
)

(instance egoPic of Feature
	(properties
		x 75
		y 160
		noun 2
		onMeCheck $0004
	)
)

(instance kissView of Actor
	(properties
		x 125
		y 159
		z -30
		view 601
		priority 15
		signal $1010
	)
)

(instance joHari of Actor
	(properties
		x 170
		y 280
		view 975
		loop 3
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(IsObject (self mover?))
				((self mover?) isMemberOf: PFollow)
			)
			((self mover?) distance: (localproc_008e))
			(self moveSpeed: (ego moveSpeed?))
			(self cycleSpeed: (ego cycleSpeed?))
		)
	)
	
	(method (cue)
		(ego normalize:)
	)
)

(instance gate of Actor
	(properties
		x 146
		y 126
		view 600
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== prevRoomNum 650) (curRoom setScript: toRm650))
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: -14 local29 -9 local29)
	)
	
	(method (doChild param1)
		(return
			(switch param1
				(-6
					(cond 
						(local29
							(if local24
								(curRoom setScript: kissing)
								(return 0)
							else
								(= local24 1)
							)
						)
						((== prevRoomNum 650) (curRoom setScript: toVillage) (return 0))
						(else (return 1))
					)
				)
				(-14
					(if local24
						(curRoom setScript: kissing)
					else
						(= local24 1)
						(messager say: 2 5 6 2)
					)
					(return 0)
				)
				(-9
					(ego addHonor: 10)
					(= local24 1)
				)
			)
		)
	)
)

(instance johariTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				11
				local29
				12
				local29
				16
				(== prevRoomNum 650)
				17
				(== prevRoomNum 650)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= prevRoomNum 650)
					(if local24
						(curRoom setScript: kissing)
					else
						(messager say: 1 2 13)
						(= local24 1)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance joHariStop of Forward
	(properties)
	
	(method (init theClient param2)
		(if argc
			(= local31 ((= client theClient) view?))
			(if (>= argc 2) (= local30 param2))
		)
		(super init: client)
		(self doit:)
	)
	
	(method (doit &tmp clientLoop clientMover)
		(if (client isStopped:)
			(cond 
				(
					(and
						(== local30 -1)
						(!= (client loop?) (- (NumLoops client) 1))
					)
					(= clientLoop (client loop?))
					(= clientMover (client mover?))
					(super doit:)
					(client
						loop: (- (NumLoops client) 1)
						setCel: clientLoop
					)
				)
				(
				(and (!= local30 -1) (== (client view?) local31))
					(client view: local30 loop: (ego loop?))
					(= clientMover (client mover?))
					(super doit:)
				)
				((!= local30 -1) (super doit:))
			)
		else
			(switch local30
				((client view?)
					(client view: local31)
				)
				(-1
					(client setLoop: -1 setCel: -1)
				)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(if (== (client view?) local30) (client view: local31))
		(super dispose:)
	)
)

(instance sFx of Sound
	(properties)
)
