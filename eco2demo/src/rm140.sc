;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	[local0 201]
)
(instance rm140 of Room
	(properties
		picture 870
		style PIXELDISSOLVE
	)
	
	(method (init)
		(LoadMany RES_VIEW 875 876 871)
		(theMusic number: 870 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						113 111
						65 111
						47 122
						34 142
						41 165
						70 180
						178 184
						234 184
						243 171
						235 166
						186 166
						135 161
						105 149
						77 147
						79 128
						134 128
						150 124
						181 124
						192 122
						192 118
						176 121
						140 117
						140 107
						180 94
						230 100
						268 109
						278 101
						180 88
						181 84
						201 71
						170 71
						175 84
						146 88
						135 89
						120 103
					yourself:
				)
		)
		(valve init:)
		(vine init:)
		(mop init: ignoreActors: TRUE addToPic:)
		(ripple init: setCycle: Forward)
		(super init: &rest)
		(self setScript: demoRm140Scr)
	)
)

(instance myLooper of Code
	
	(method (doit param1 param2 obj &tmp [temp0 2])
		(ego loop: 5)
		(if (and (> argc 2) (IsObject obj))
			(obj cue:)
		)
	)
)

(instance demoRm140Scr of Script
	
	(method (doit)
		(if (and (OneOf state 6 7 8) (!= (ego loop?) 5))
			(ego loop: 5)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 140))
			(2
				(theText init:)
				(= ticks 180)
			)
			(3
				(theText dispose:)
				(= ticks 20)
			)
			(4
				(theText cel: 1 init:)
				(= ticks 180)
			)
			(5
				(theText dispose:)
				(= ticks 20)
			)
			(6
				(ego
					posn: 219 114
					init:
					normalize: 0
					setLoop: 5
					setPri: 1
					setScale: Scaler 10 100 109 82
				)
				(self setScript: egoSpeedManage)
				(= cycles 2)
			)
			(7
				((ego looper?) dispose:)
				(ego looper: myLooper)
				(= cycles 2)
			)
			(8
				(ego
					signal: (| (ego signal?) notUpd)
					setMotion: DPath 205 97 195 85 187 81 180 80 self
				)
			)
			(9
				(script dispose:)
				(ego
					looper: register
					setLoop: -1
					setPri: -1
					setScale:
					normalize:
					setMotion: MoveTo 160 96 self
				)
			)
			(10 (= cycles 2))
			(11
				(ego
					view: 2
					loop: 12
					cel: 0
					cycleSpeed: 13
					setCycle: EndLoop self
				)
			)
			(12 (= ticks 45))
			(13
				(ego cycleSpeed: 9 setCycle: BegLoop self)
			)
			(14 (= ticks 30))
			(15
				(ego normalize: 0 5)
				(= cycles 2)
			)
			(16
				(self setScript: vineScript self)
			)
			(17 (= ticks 60))
			(18
				(theMusic fade:)
				(= ticks 120)
			)
			(19 (curRoom newRoom: 150))
		)
	)
)

(instance egoSpeedManage of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (GetDistance (ego x?) (ego y?) 219 114))
				(= temp0 (- 10 (/ temp0 12)))
				(if (!= (ego moveSpeed?) temp0) (ego moveSpeed: temp0))
				(-- state)
				(= cycles 2)
			)
		)
	)
)

(instance egoStance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theDoits add: self)
				(ego view: 0 loop: 8 cel: 0 posn: 210 108)
				(= cycles 2)
			)
			(1
				(ego view: 2 loop: 4 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(2
				(theDoits delete: self)
				(super dispose:)
			)
		)
	)
)

(instance vineScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 196 118 self)
			)
			(1 (= cycles 2))
			(2
				((ego looper?) dispose:)
				(ego looper: 0)
				(egoStance client: self)
				(ego
					setPri: 13
					view: 871
					loop: 7
					posn: 210 108
					cel: 0
					setCycle: EndLoop egoStance
				)
				(soundFx number: 871 loop: -1 play:)
				(vine cycleSpeed: 10 setCycle: CycleTo 5 1 self)
			)
			(3
				(vine
					setLoop: 1
					cel: 0
					setPri: (- (ego priority?) 1)
					setCycle: EndLoop self
				)
				(vineFront
					init:
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(4 0)
			(5
				(soundFx number: 872 loop: 1 play:)
				(vineFront dispose:)
				(ego hide:)
				(vine view: 871 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(6 (self dispose:))
		)
	)
)

(instance vine of Prop
	(properties
		x 270
		y 19
		view 875
		priority 13
		signal fixPriOn
	)
)

(instance vineFront of Prop
	(properties
		x 270
		y 19
		view 875
		loop 2
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance valve of Prop
	(properties
		x 213
		y 91
		view 870
		loop 6
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance ripple of Actor
	(properties
		x 234
		y 41
		view 870
		cycleSpeed 13
	)
)

(instance mop of View
	(properties
		x 259
		y 25
		view 876
		loop 1
		priority 14
		signal fixPriOn
	)
)

(instance theText of View
	(properties
		y 167
		view 93
		loop 3
		priority 15
		signal fixPriOn
	)
	
	(method (init)
		(= x (/ (- 320 (CelWide view loop cel)) 2))
		(super init: &rest)
	)
)
