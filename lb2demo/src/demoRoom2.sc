;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Intrface)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom2 0
)

(instance demoRoom2 of Room
	(properties
		picture 210
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 220)
		(LoadMany VIEW 212 213 220 221 803 1220 1221)
		(ego
			view: 803
			loop: 5
			posn: 197 176
			cycleSpeed: 13
			moveSpeed: 13
			init:
		)
		(tempObj init:)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						319 189
						319 176
						197 176
						177 183
						170 179
						153 165
						116 174
						157 182
						73 183
						55 172
						32 172
						49 189
					yourself:
				)
		)
		(sam init: hide:)
		(samMouth init: hide:)
		(legs init: hide:)
		(fan init: hide:)
		(shadowL init: hide:)
		(shadowR init: hide:)
		(man1 init:)
		(person2 init:)
		(person3 init:)
		(person4 init:)
		(person6 init:)
		(person7 init:)
		(person8 init:)
		(car init:)
		(car2 init:)
		(door210 init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(music number: 1000 loop: -1 play:)
				(ego setMotion: PolyPath 34 174 tempObj)
				(man1 setScript: animateActors)
				(= cycles 1)
			)
			(1
				(Print 2 0 #at 120 20 #width 170 #font 69 #dispose)
				(= seconds 8)
			)
			(2
				(Print 2 1 #at 120 20 #width 170 #font 69 #dispose)
				(= seconds 10)
			)
			(3
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(ego
					view: 1221
					loop: 0
					cel: 0
					setPri: 14
					posn: 131 162
					setCycle: 0
					setMotion: 0
				)
				(man1 dispose:)
				(person2 dispose:)
				(person3 dispose:)
				(person4 dispose:)
				(person6 dispose:)
				(person7 dispose:)
				(person8 dispose:)
				(car dispose:)
				(car2 dispose:)
				(curRoom drawPic: 220 6)
				(sam show:)
				(samMouth show: setCycle: RandCycle)
				(legs posn: (+ (ego x?) 42) (+ (ego y?) 17) show:)
				(fan show: setCycle: Forward)
				(shadowL show: setScript: sLeftShadow)
				(shadowR show: setScript: sRightShadow)
				(= cycles 2)
			)
			(4
				(LoadMany FALSE 212 213)
				(Print 2 2 #dispose #at 10 10 #width 150 #font 69)
				(= seconds 8)
			)
			(5
				(music fade:)
				(= seconds 2)
			)
			(6
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(UnLoad PICTURE 210 220)
				(ego cycleSpeed: 6 moveSpeed: 6)
				(curRoom newRoom: 3)
			)
		)
	)
)

(instance animateActors of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person8
					setLoop: 11
					setCycle: Walk
					setMotion: PolyPath 330 184 person8
				)
				(person7
					setLoop: 10
					setCycle: Walk
					setMotion: PolyPath 390 189 person7
				)
				(person2
					setLoop: 2
					setStep: 1 1
					setCycle: Walk
					setMotion: PolyPath -10 183 person2
				)
				(man1
					setLoop: 0
					setCycle: Walk
					setMotion: PolyPath 198 176 man1
				)
				(person3
					setLoop: 3
					setStep: 1 1
					setCycle: Walk
					setMotion: MoveTo 202 176 person3
				)
				(car setLoop: 0 setMotion: MoveTo 370 181 car)
				(car2 setLoop: 7 setMotion: MoveTo 139 173 car2)
			)
			(1
				(person4
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo 319 166 person4
				)
				(person6
					setLoop: 7
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo 319 167 person6
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sComeBackOn2 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person2
					posn: 0 183
					setStep: 2 2
					setLoop: 3
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: Walk
					setMotion: PolyPath 210 186 self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sComeBackOn4 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person4
					posn: 319 166
					setLoop: 6
					setCycle: Walk
					setMotion: PolyPath 100 166 self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sComeBackOn6 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person6
					posn: 319 167
					setLoop: 9
					setStep: 2 2
					setCycle: Walk
					setMotion: PolyPath 100 167 self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sRightShadow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shadowR setLoop: 1 setMotion: MoveTo 177 86 self)
			)
			(1
				(= seconds (Random 1 4))
			)
			(2
				(shadowR posn: -20 89)
				(self init:)
			)
		)
	)
)

(instance sLeftShadow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 2)))
			(1
				(shadowL setLoop: 0 setMotion: MoveTo -30 89 self)
			)
			(2
				(shadowL posn: 175 85)
				(self init:)
			)
		)
	)
)

(instance sam of View
	(properties
		x 255
		y 109
		view 1220
	)
	
	(method (init)
		(samMouth posn: (sam x?) (- (sam y?) 31))
		(super init:)
	)
)

(instance samMouth of Prop
	(properties
		view 1220
		loop 1
		priority 8
		signal fixPriOn
	)
)

(instance legs of Prop
	(properties
		view 1221
		cel 1
	)
)

(instance fan of Prop
	(properties
		x 10
		y 177
		view 220
	)
)

(instance shadowL of Actor
	(properties
		x 175
		y 85
		view 221
		priority 1
		signal (| ignrAct fixPriOn)
		moveSpeed 4
	)
)

(instance shadowR of Actor
	(properties
		x -20
		y 89
		view 221
		loop 1
		priority 1
		signal (| ignrAct fixPriOn)
		moveSpeed 4
	)
)

(instance tempObj of Actor
	(properties
		x -10
		y -1000
		view 803
		loop 5
	)
	
	(method (cue)
		(super cue:)
		(ego setHeading: 1)
		(door210 setCycle: EndLoop)
	)
)

(instance man1 of Actor
	(properties
		x 117
		y 188
		view 212
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(man1
			setPri: 12
			loop: 1
			posn: 194 175
			setMotion: MoveTo 166 176
		)
	)
)

(instance person2 of Actor
	(properties
		x 130
		y 180
		view 212
		loop 2
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person2 setScript: sComeBackOn2)
	)
)

(instance person3 of Actor
	(properties
		x 101
		y 183
		view 212
		loop 3
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person3 loop: 4 setMotion: MoveTo 170 170)
	)
)

(instance person4 of Actor
	(properties
		x 200
		y 166
		view 212
		loop 5
		priority 8
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person4 setScript: sComeBackOn4)
	)
)

(instance person6 of Actor
	(properties
		x 213
		y 167
		view 212
		loop 7
		priority 8
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person6 setScript: sComeBackOn6)
	)
)

(instance person7 of Actor
	(properties
		x 224
		y 177
		view 212
		loop 10
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person7 dispose:)
	)
)

(instance person8 of Actor
	(properties
		x 51
		y 189
		view 212
		loop 11
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(person8 dispose:)
	)
)

(instance car of Actor
	(properties
		x 154
		y 189
		view 213
		cel 1
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(car dispose:)
	)
)

(instance car2 of Actor
	(properties
		x 297
		y 173
		view 213
		loop 7
		cel 3
		priority 10
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (init)
		(wheels init: setLoop: 9 setPri: 11 posn: x (+ y 2) 1)
		(super init:)
	)
	
	(method (dispose)
		(wheels dispose:)
		(super dispose:)
	)
	
	(method (setMotion mType)
		(super setMotion: mType &rest)
		(if (IsObject mType)
			(wheels setMotion: mType &rest)
			(wheels setCycle: Forward)
		)
	)
)

(instance wheels of Actor
	(properties
		view 213
	)
)

(instance door210 of Prop
	(properties
		x 39
		y 166
		view 210
	)
)
