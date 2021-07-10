;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use CyclingProp)
(use LastLink)
(use LoadMany)
(use QSound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	introRm 0
)

(local
	introWater
	subNose
	introWater2
	subNose2
	scope
	plant1
	plant2
	plant3
	sierraLogo
	presents
	missile
	theS
	theI
	theE
	theR1
	theR2
	theA
)
(procedure (AddViews theView theLoop theCel theX theY)
	(views
		add:
			((View new:)
				view: theView
				loop: theLoop
				cel: theCel
				x: theX
				y: theY
				init:
				setPri: 12
				yourself:
			)
	)
)

(instance introRm of Room
	(properties
		picture 299
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(LoadMany VIEW 56 60 400 500 600 700 800 899)
		(Load PICTURE 100)
		(HandsOff)
		((= introWater (CyclingProp new:))
			view: 899
			loop: 0
			x: 220
			y: 19
			cycleSpeed: 1
			init:
		)
		((= introWater2 (CyclingProp new:))
			view: 899
			loop: 2
			x: 285
			y: 11
			cycleSpeed: 1
			init:
		)
		((= subNose (CyclingProp new:))
			view: 899
			loop: 1
			x: 184
			y: 62
			cycleSpeed: 1
			init:
		)
		((= subNose2 (CyclingProp new:))
			view: 899
			loop: 3
			x: 118
			y: 82
			cycleSpeed: 1
			init:
		)
		((= scope (CyclingProp new:))
			view: 899
			loop: 4
			x: 148
			y: 44
			cycleSpeed: 1
			init:
		)
		((= plant1 (CyclingProp new:))
			view: 56
			loop: 6
			x: 140
			y: 182
			cycleSpeed: 1
			init:
		)
		((= plant2 (CyclingProp new:))
			view: 56
			loop: 6
			x: 47
			y: 152
			cycleSpeed: 1
			init:
		)
		((= plant3 (CyclingProp new:))
			view: 56
			loop: 8
			x: 129
			y: 184
			cycleSpeed: 1
			init:
		)
		(if (!= howFast fast)
			(introWater addToPic:)
			(introWater2 addToPic:)
			(subNose addToPic:)
			(subNose2 addToPic:)
			(scope addToPic:)
			(plant1 addToPic:)
			(plant2 addToPic:)
			(plant3 addToPic:)
		)
		(self setScript: introScript)
	)
	
	(method (dispose)
		(views dispose:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent)
		(theQueuedSound dispose:)
		(self newRoom: 1)
	)
	
	(method (cue)
		(if script
			((LastLink 118 self) cue:)
		)
	)
)

(instance introScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(subBubbles init: 126 87)
				((= theQueuedSound (QueuedSound new:))
					number:
					(switch numVoices
						(1 401)
						(3 201)
						(else  1)
					)
					init:
					owner: theGame
					play: client
				)
				(= seconds 2)
			)
			(1
				(theGame setCursor: theCursor FALSE)
				((= sierraLogo (Actor new:))
					view: 600
					loop: 0
					setLoop:
					cel: 0
					x: 181
					y: 210
					setPri: 9
					ignoreActors: TRUE
					ignoreControl: cWHITE
					init:
				)
				(if (!= howFast fast)
					(sierraLogo posn: 181 129)
				else
					(sierraLogo setMotion: MoveTo 181 129)
				)
				(= seconds 7)
			)
			(2
				((= theS (Prop new:))
					view: 600
					loop: 2
					x: 123
					y: 130
					init:
					setPri: 12
					stopUpd:
				)
				((= theI (Prop new:))
					view: 600
					loop: 3
					x: 141
					y: 145
					setPri: 12
					init:
					stopUpd:
				)
				((= theE (Prop new:))
					view: 600
					loop: 4
					x: 157
					y: 145
					setPri: 12
					init:
					stopUpd:
				)
				((= theR1 (Prop new:))
					view: 600
					loop: 5
					x: 180
					y: 145
					setPri: 12
					init:
					stopUpd:
				)
				((= theR2 (Prop new:))
					view: 600
					loop: 5
					x: 207
					y: 145
					setPri: 12
					init:
					stopUpd:
				)
				((= theA (Prop new:))
					view: 600
					loop: 6
					x: 236
					y: 145
					setPri: 12
					init:
					stopUpd:
				)
				(sierraLogo dispose:)
				((= presents (Actor new:))
					view: 600
					setLoop: 1
					cel: 0
					posn: 212 210
					setPri: 12
					ignoreActors: TRUE
					ignoreControl: cWHITE
					init:
				)
				(if (!= howFast fast)
					(presents posn: 212 160)
				else
					(presents setMotion: MoveTo 212 160)
				)
				(= seconds 2)
			)
			(3)
			(4
				(if (== howFast fast)
					((= missile (Actor new:))
						view: 800
						loop: 0
						cel: 0
						x: 50
						y: 147
						xStep: 25
						init:
						setScript: missleScript
						setPri: 12
					)
					(= seconds 1)
				else
					(= cycles 1)
				)
			)
			(5
				(if (== howFast 0)
					(theS dispose:)
					(theI dispose:)
					(theE dispose:)
					(theR1 dispose:)
					(theR2 dispose:)
					(theA dispose:)
					(presents dispose:)
					(self changeState: 10)
				else
					(theS setCycle: EndLoop)
					(= cycles (if (!= howFast fast) 1 else 3))
				)
			)
			(6
				(theI setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(7
				(if (== howFast fast)
					(missile xStep: 20)
				)
				(theE setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(8
				(if (== howFast fast)
					(missile xStep: 18)
				)
				(theR1 setCycle: EndLoop)
				(presents setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(9
				(if (== howFast fast)
					(missile xStep: 16)
				)
				(theR2 setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(10
				(switch howFast
					(fast
						(missile xStep: 2)
						(theA setCycle: EndLoop)
					)
					(medium
						(theA setCycle: EndLoop)
					)
				)
			)
			(11
				(AddViews 700 3 0 114 130)
			)
			(12
				(AddViews 700 3 1 126 130)
			)
			(13
				(AddViews 700 3 2 137 130)
			)
			(14
				(AddViews 700 3 3 148 130)
			)
			(15
				(AddViews 700 3 4 164 130)
			)
			(16
				(AddViews 700 3 5 177 130)
			)
			(17
				(AddViews 700 3 6 191 130)
			)
			(18
				(AddViews 700 3 3 204 130)
			)
			(19
				(AddViews 400 4 7 214 130)
			)
			(20
				(ice init:)
				(man init:)
			)
			(21
				(cast eachElementDo: #dispose)
				(views release:)
				(curRoom drawPic: 100)
				(curRoom newRoom: 102)
			)
		)
	)
)

(instance ice of Actor
	(properties
		y 225
		x 140
		view 700
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setLoop: loop setPri: 11)
		(if (!= howFast fast)
			(self posn: 140 172 cue:)
		else
			(self setMotion: MoveTo 140 172 self)
		)
	)
	
	(method (cue)
		(Display 100 0
			p_at 108 182
			p_color vWHITE
		)
		(self stopUpd:)
		(bubbles init:)
		(ray init:)
		(subBubbles init: 166 66)
	)
)

(instance man of Actor
	(properties
		y 224
		x 226
		view 700
		loop 2
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setLoop: loop setPri: 11)
		(if (!= howFast fast)
			(self posn: 226 171 cue:)
		else
			(self setMotion: MoveTo 226 171 self)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance ray of Actor
	(properties
		y 179
		x 139
		view 60
		loop 2
		xStep 5
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 12
			setLoop: loop
			setCycle: Walk
			ignoreActors: TRUE
			ignoreControl: cWHITE
			setScript: rayScript
		)
	)
)

(instance rayScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 87 158 self)
			)
			(1
				(client
					setLoop: 4
					setCel: 0
					setPri: 10
					setCycle: EndLoop self
				)
			)
			(2
				(client
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 340 143 self
				)
			)
			(3
				(client hide: setScript: 0)
			)
		)
	)
)

(instance missleScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setPri: 14
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: MoveTo 400 (client y?)
					setCycle: EndLoop self
				)
			)
			(1
				(client setLoop: 1 setCycle: EndLoop self)
			)
			(2
				((= register (Prop new:))
					view: 800
					loop: 2
					x: 152
					y: 152
					init:
					setCycle: EndLoop self
				)
				(client hide:)
			)
			(3
				(register dispose:)
			)
		)
	)
)

(instance bubbles of Actor
	(properties
		y 179
		x 139
		view 899
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 5
			ignoreActors: TRUE
			setPri: 12
			setCycle: Walk
			ignoreControl: cWHITE
			setMotion: MoveTo 139 -10 self
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance subBubbles of Actor
	(properties
		view 899
	)
	
	(method (init toX toY)
		(super init:)
		(self
			posn: toX toY
			setLoop: 6
			setPri: 3
			ignoreActors: TRUE
			setCycle: Walk
			ignoreControl: cWHITE
			setMotion: MoveTo toX -10 self
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance views of Collection)
