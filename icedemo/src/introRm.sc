;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO)
(include game.sh)
(use Main)
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

(instance introWater of Prop
	(properties
		y 19
		x 220
		view 899
		signal ignrAct
		cycleSpeed 1
	)
)

(instance introWater2 of Prop
	(properties
		y 11
		x 285
		view 899
		loop 2
		signal ignrAct
		cycleSpeed 1
	)
)

(instance subNose of Prop
	(properties
		y 62
		x 184
		view 899
		loop 1
		signal ignrAct
		cycleSpeed 1
	)
)

(instance subNose2 of Prop
	(properties
		y 82
		x 118
		view 899
		loop 3
		signal ignrAct
		cycleSpeed 1
	)
)

(instance scope of Prop
	(properties
		y 44
		x 148
		view 899
		loop 4
		signal ignrAct
		cycleSpeed 1
	)
)

(instance plant1 of Prop
	(properties
		y 182
		x 140
		view 56
		loop 6
		signal ignrAct
		cycleSpeed 1
	)
)

(instance plant2 of Prop
	(properties
		y 152
		x 47
		view 56
		loop 6
		signal ignrAct
		cycleSpeed 1
	)
)

(instance plant3 of Prop
	(properties
		y 184
		x 129
		view 56
		loop 8
		signal ignrAct
		cycleSpeed 1
	)
)

(instance introRm of Room
	(properties
		picture 299
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 56 60 400 500 600 700 800 899)
		(Load PICTURE 100)
		(HandsOff)
		(introWater setCycle: Forward init:)
		(introWater2 setCycle: Forward init:)
		(subNose setCycle: Forward init:)
		(subNose2 setCycle: Forward init:)
		(scope setCycle: Forward init:)
		(plant1 setCycle: Forward init:)
		(plant2 setCycle: Forward init:)
		(plant3 setCycle: Forward init:)
		(self setScript: introScript)
	)
	
	(method (dispose)
		(views dispose:)
		(theQueuedSound dispose:)
		(DisposeScript QSOUND)
		(DisposeScript LASTLINK)
		(super dispose:)
	)
	
	(method (cue)
		(if script ((LastLink 121 self) cue:))
	)
)

(instance introScript of Script
	(properties)
	
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
				(theGame setCursor: theCursor 0)
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
						setScript: missileScript
						setPri: 12
					)
					(= seconds 1)
				else
					(= cycles 1)
				)
			)
			(5
				(if (== howFast slow)
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
				(if (== howFast fast) (missile xStep: 20))
				(theE setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(8
				(if (== howFast fast) (missile xStep: 18))
				(theR1 setCycle: EndLoop)
				(presents setCycle: EndLoop)
				(= cycles (if (!= howFast fast) 1 else 3))
			)
			(9
				(if (== howFast fast) (missile xStep: 16))
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
			(20 (ice init:) (man init:))
			(21
				(cast eachElementDo: #dispose)
				(views release:)
				(curRoom newRoom: 1)
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
		(Display 100 0 p_at 108 182 p_color vWHITE)
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
	(properties)
	
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
			(3 (client hide: setScript: 0))
		)
	)
)

(instance missileScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setPri: 14
					ignoreActors: 1
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
			(3 (register dispose:))
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
	
	(method (init theX theY)
		(super init:)
		(self
			posn: theX theY
			setLoop: 6
			setPri: 3
			ignoreActors: TRUE
			setCycle: Walk
			ignoreControl: cWHITE
			setMotion: MoveTo theX -10 self
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance views of Collection
	(properties)
)
