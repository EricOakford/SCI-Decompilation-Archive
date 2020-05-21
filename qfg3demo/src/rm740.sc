;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include game.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm740 0
)

(local
	[local0 4]
)
(instance rm740 of Room
	(properties
		picture 740
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 67 31 73 61 83 97 91 127 95 149 91 170 78 169 71 161 74
						129 85 108 77 120 65 135 52 141 37 130 35 105 39 108 50
						100 58 68 62 42 58 13 60 0 60 0 0 319 0 319 62 297 67 255 65
						236 70 245 94 291 90 319 91 319 189 0 189
					yourself:
				)
		)
		(vine init:)
		(vine2 setPri: 14 init:)
		(spray init: setCycle: Forward)
		(ego
			view: 0
			loop: 0
			x: 147
			y: 79
			init:
			setStep: 3 2
			setScale: Scaler 100 60 189 60
		)
		(self setScript: seeMeGo)
	)
	
	(method (doit)
		(Palette PALCycle 227 235 -1)
		(if (curRoom script?) (script doit:))
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany FALSE POLYPATH SCALER)
		(super dispose:)
	)
	
	(method (cue)
		(bridge dispose:)
		(DoAudio Stop)
		(curRoom newRoom: 600)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 5])
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(Graph GDrawLine 36 124 36 283 0 -1 -1)
				(Graph GShowBits 35 123 37 284 1)
				(bridge init:)
				(music number: 14 play: curRoom)
				(DoAudio Play 16)
				(= seconds 1)
			)
			(2
				(ego
					setCycle: Walk
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: PolyPath 166 71 self
				)
			)
			(3
				(ego
					view: 39
					y: 11
					setCycle: Forward
					setMotion: MoveTo 244 11 self
				)
				(Display {EXPERIENCE THE EXCITEMENT}
					p_font 2510
					p_at 36 111
					p_color 25
				)
				(Display {EXPERIENCE THE EXCITEMENT}
					p_font 2510
					p_at 35 110
					p_color 30
				)
			)
			(4
				(ego
					view: 0
					x: (+ (ego x?) 4)
					y: 75
					setCycle: Walk
					setMotion: MoveTo 350 83
				)
			)
			(5 0)
		)
	)
)

(instance bridge of Feature
	(properties
		x 200
		y 38
		nsTop 32
		nsLeft 120
		nsBottom 45
		nsRight 286
		sightAngle 40
	)
)

(instance rope of Prop
	(properties
		x 170
		y 79
		view 744	;this view is absent
		signal ignrAct
	)
)

(instance vine of View
	(properties
		x 31
		y 51
		view 740
	)
)

(instance vine2 of View
	(properties
		x 1
		y 1
		view 740
		cel 1
	)
)

(instance spray of Prop
	(properties
		x 202
		y 173
		view 740
		loop 1
	)
)
