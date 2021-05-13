;;; Sierra Script 1.0 - (do not remove this comment)
(script# 180)
(include sci.sh)
(use Main)
(use LBRoom)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm180 0
)

(instance rm180 of LBRoom
	(properties
		picture 180
		style $000a
		vanishingX 145
		vanishingY 126
	)
	
	(method (init)
		(LoadMany 128 151 181 185)
		(LoadMany 132 94 180)
		(self setRegions: 92)
		(ego
			view: 185
			setLoop: 5
			x: 325
			y: 195
			xStep: 1
			yStep: 1
			setPri: -1
			init:
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 305 189 222 168 319 175 319 189
					yourself:
				)
		)
		(nyCar3 addToPic:)
		(nyCar4 addToPic:)
		(WrapMusic init: 0 180 94)
		(nyCar1 init: setScript: sCarGo1)
		(p1 init: setScript: sP1Walk)
		(p2
			init:
			setLoop: 1
			setCycle: Walk
			setMotion: PolyPath 31 220
		)
		(p3
			init:
			setLoop: 2
			setCycle: Walk
			setMotion: PolyPath 137 172
		)
		(p4
			init:
			setLoop: 4
			setCycle: Walk
			setMotion: PolyPath 206 206
		)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: Walk setMotion: PolyPath 224 168 self)
			)
			(1
				((ScriptID 1881 2) modeless: 1)
				(= cycles 2)
			)
			(2 (messager say: 1 0 0 0 self))
			(3
				(ego setLoop: 6 setMotion: PolyPath 245 168 self)
			)
			(4 (= seconds 2))
			(5
				(ego dispose:)
				(creditTitle
					init:
					posn: 48 -20
					setPri: 15
					setMotion: MoveTo 48 93 self
				)
				(creditName
					init:
					posn: 62 245
					setPri: 15
					setMotion: MoveTo 62 132 self
				)
			)
			(6 0)
			(7 (= seconds 3))
			(8
				(creditTitle setMotion: MoveTo 48 -20 self)
				(creditName setMotion: MoveTo 62 210 self)
			)
			(9 0)
			(10
				(creditTitle
					loop: 8
					posn: -190 102
					setPri: -1
					setMotion: MoveTo 50 102 self
				)
				(creditName
					loop: 8
					posn: -190 138
					setPri: -1
					setMotion: MoveTo 50 138 self
				)
			)
			(11 0)
			(12 (= seconds 3))
			(13
				(creditTitle setMotion: MoveTo 465 102 self)
				(creditName setMotion: MoveTo 456 138 self)
			)
			(14 0)
			(15
				(creditTitle
					loop: 9
					posn: 320 83
					setPri: 15
					setMotion: MoveTo 48 83
				)
				(creditName
					loop: 9
					posn: 356 122
					setPri: 15
					setMotion: MoveTo 82 122 self
				)
			)
			(16 (= seconds 3))
			(17
				(creditTitle setMotion: MoveTo 48 -50 self)
				(creditName setMotion: MoveTo 82 250 self)
			)
			(18 0)
			(19
				(curRoom newRoom: 190)
				(theGame setCursor: normalCursor)
				(self dispose:)
			)
		)
	)
)

(instance sCarGo1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(nyCar1 setMotion: MoveTo -25 168 self)
			)
			(1
				(nyCar1
					loop: 0
					cel: 2
					posn: -25 182
					setMotion: MoveTo 120 158 self
				)
			)
			(2
				(nyCar1 addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance sP1Walk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(p1
					setLoop: 0
					setCycle: Walk
					setMotion: PolyPath 136 170 self
				)
			)
			(1
				(p1
					setLoop: 3
					posn: 158 170
					setMotion: PolyPath 220 220 self
				)
			)
			(2
				(p1 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance nyCar1 of Actor
	(properties
		x 155
		y 153
		view 181
		loop 3
		cel 4
		signal $4800
	)
)

(instance p1 of Actor
	(properties
		x 197
		y 198
		yStep 1
		view 185
		xStep 1
	)
)

(instance p2 of Actor
	(properties
		x 138
		y 168
		yStep 1
		view 185
		loop 1
		xStep 1
	)
)

(instance p3 of Actor
	(properties
		x 186
		y 198
		yStep 1
		view 185
		loop 2
		xStep 1
	)
)

(instance p4 of Actor
	(properties
		x 155
		y 166
		yStep 1
		view 185
		loop 4
		xStep 1
	)
)

(instance creditTitle of Actor
	(properties
		x -156
		y 102
		view 151
		loop 7
		signal $6800
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x -190
		y 138
		view 151
		loop 7
		cel 1
		signal $6800
		moveSpeed 0
	)
)

(instance nyCar3 of View
	(properties
		x 235
		y 153
		view 181
		loop 2
		signal $4800
	)
)

(instance nyCar4 of View
	(properties
		x 230
		y 200
		view 181
		loop 5
		cel 1
		signal $4800
	)
)
