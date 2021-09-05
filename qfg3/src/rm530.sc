;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include game.sh)
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm530 0
)

(local
	local0 = [0 5 -6 999]
	[local4 2]
	local6 = [0 3 4 2 999]
	[local11 2]
)
(instance rm530 of Room
	(properties
		picture 400
		vanishingY -200
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						117
						87
						135
						161
						145
						158
						152
						115
						153
						138
						174
						222
						186
						316
						185
						317
						168
						264
						148
						319
						147
						319
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 122 137 123 0 101 0 0 319 0
					yourself:
				)
		)
		(= [local4 0] @local0)
		(egoTeller init: ego @local0 @local4)
		(ego
			x: 330
			y: 150
			changeGait: 1
			normalize:
			setScale: 190
			noun: 2
			init:
		)
		(= [local11 0] @local6)
		(yesufuTeller init: (ScriptID 39 1) @local6 @local11)
		((ScriptID 39 1)
			view: 501
			loop: 0
			cel: 4
			x: 215
			y: 166
			setScale: 190
			setCycle: OccasionalCycle self 1 70 130
			noun: 1
			init:
		)
		(super init:)
		(tree addToPic:)
		(grass addToPic:)
		(rock addToPic:)
		(tree2 addToPic:)
		(rock3 addToPic:)
		(rock4 addToPic:)
		(trap addToPic:)
		(curRoom setScript: enterRoom)
	)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((<= (ego x?) 5) (curRoom setScript: exitEgo))
			((>= (ego x?) 315) (curRoom setScript: exitEgoR))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(DisposeScript 39)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(65 (messager say: 3 6 7))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 200 150 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance freeYesufu of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 1 4 0 0 self)
			)
			(1
				(Bset 30)
				(ego
					setMotion: PolyPath 215 156 self
					solvePuzzle: 287 8 9
				)
			)
			(2
				(ego view: 4 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				((ScriptID 39 1) setLoop: 1 cel: 0 setCycle: EndLoop self)
				(ego setCycle: BegLoop self)
			)
			(4)
			(5 (messager say: 1 6 1 0 self))
			(6
				(ego normalize: setMotion: PolyPath 200 128 self)
				((ScriptID 39 1)
					view: 983
					setCycle: StopWalk -1
					setLoop: -1
					setLoop: yesufuStopGroop
					setStep: 6 4
					xStep: 6
					yStep: 4
					setMotion: PolyPath 200 140
				)
			)
			(7
				(ego setMotion: PolyPath -20 103 self)
				((ScriptID 39 1) setMotion: PolyPath -20 114)
			)
			(8
				(ego addHonor: 50)
				(curRoom newRoom: 500)
			)
		)
	)
)

(instance exitEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (- (ego x?) 20) (ego y?) self)
			)
			(1 (curRoom newRoom: 500))
		)
	)
)

(instance exitEgoR of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 20) (ego y?) self)
			)
			(1 (curRoom newRoom: 500))
		)
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (doChild)
		(if (== query -6)
			(ego setAvoider: PAvoider setMotion: PolyPath -10 103)
		)
	)
)

(instance yesufuTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (curRoom script?)
					0
				else
					(curRoom setScript: freeYesufu)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tree of View
	(properties
		x 240
		y 114
		view 400
		loop 2
		cel 1
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance grass of View
	(properties
		x 268
		y 200
		view 400
		loop 1
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance rock of View
	(properties
		x 311
		y 120
		view 400
		cel 2
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance tree2 of View
	(properties
		x 161
		y 75
		view 400
		cel 1
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance rock2 of View
	(properties
		x 9
		y 57
		view 400
		cel 2
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance rock3 of View
	(properties
		x 33
		y 76
		view 400
		cel 2
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance rock4 of View
	(properties
		x 36
		y 145
		view 400
		cel 2
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance trap of View
	(properties
		x 194
		y 153
		view 401
		loop 3
		cel 1
		signal $4000
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance yesufuStopGroop of GradualLooper
	(method (doit)
		(if
			(and
				(IsObject ((ScriptID 39 1) cycler?))
				(((ScriptID 39 1) cycler?) isKindOf: StopWalk)
			)
			((ScriptID 39 1)
				view: (((ScriptID 39 1) cycler?) vWalking?)
			)
		)
		(super doit: &rest)
	)
)
