;;; Sierra Script 1.0 - (do not remove this comment)
(script# rCastleBarracks)
(include game.sh)
(use Main)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm38 0
)

(instance rm38 of Room
	(properties
		picture 38
		style WIPELEFT
		horizon 60
	)
	
	(method (init &tmp theY)
		(super init: &rest)
		(StatusLine enable:)
		(mouseDownHandler add: self)
		(self
			setFeatures: onBarracks onFence onMountains onTrees onWall1
		)
		(NormalEgo)
		(if (not Night)
			(guardHead setScript: guardSleeps)
			(guardBody init:)
		)
		(= theY (ego y?))
		(ego
			posn: 319 theY
			init:
			setMotion: MoveTo 305 (if (< theY 115) 115 else theY)
		)
		(self setLocales: CASTLE)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== (ego edgeHit?) EAST)
			(if (> (ego y?) 125)
				(curRoom newRoom: 39)
			else
				(= temp0 (- (ego y?) 84))
				(ego y: (+ (* temp0 2) 106))
				(curRoom newRoom: 41)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn38)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[/!*]')
								(HighPrint 38 0)
								;This is the barracks area.
							)
							((Said '/castle')
								(HighPrint 38 1)
								;The castle is northeast of here
							)
							((Said '/barrack')
								(HighPrint 38 2)
								;The building's appearance is deceptive; it evidently extends well into the castle wall.
								;From your present vantage point, the barracks looks like it could house some sixty guards.
							)
							((Said '/north')
								(HighPrint 38 3)
								;You see the castle and the guards.
							)
							((Said '/east')
								(HighPrint 38 4)
								;You see the main courtyard of the castle and the stables beyond.
							)
							((Said '/south')
								(HighPrint 38 5)
								;Along the wall to the southeast is the gatehouse.  A line of bushes partially obscure a defensive pit along the wall.
							)
							((Said '/west')
								(HighPrint 38 6)
								;You can see the barracks and a man sitting in front of them.
							)
						)
					)
					((Said 'climb')
						(HighPrint 38 7)
						;The guard would object.
					)
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(HighPrint 38 8)
						;You barely get to sleep when the guard on night patrol kicks you out.
						(if (and (< 750 Clock) (< Clock 2550))
							(FixTime 21)
						)
						(curRoom newRoom: 37)
					)
				)
			)
		)
	)
)

(instance guardHead of Actor
	(properties
		y 113
		x 197
		view rCastleBarracks
		loop 1
		cycleSpeed 6
	)
	
	(method (doit)
		(cond 
			(
				(and
					(< 118 (ego y?))
					(< (ego y?) 150)
					(< (ego x?) 245)
					(!= script guardThreatens)
				)
				(self setScript: guardThreatens)
			)
			(
				(and
					(not
						(if (and (< 118 (ego y?)) (< (ego y?) 150))
							(< (ego x?) 245)
						)
					)
					(< 114 (ego y?))
					(< (ego y?) 178)
					(< (ego x?) 290)
					(!= script guardAwakes)
				)
				(self setScript: guardAwakes)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed guardBody event shiftDown)
					(HighPrint 38 9)
					;The guard looks like he is either very tired or very lazy.
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said '/hey')
						(if (== script guardSleeps)
							(HighPrint 38 10)
							;You're not close enough.  He's hard of hearing.
						else
							(HighPrint 38 11)
							;"Hay is for horses."
						)
					)
					((Said 'fight/guard,man,gatekeeper')
						(if (== script guardSleeps)
							(HighPrint 38 10)
							;You're not close enough.  He's hard of hearing.
						else
							(HighPrint 38 12)
							;"If you want to fight, go talk to the Weapons Master."
						)
					)
					((Said 'kill/guard,man,gatekeeper')
						(EgoDead 38 13
							#title {OH NOOOOOOO!}
							#icon rCastleCourtyard 3 0
							;As you approach the guard with your weapon, he jumps up and opens the barracks door as he yells for help.
							;Four guards playing cards inside the barracks quickly grab their weapons and overwhelm you.
							;As you are dragged into the castle and down into the dungeon, you realize that was probably not quite the right thing to do.
						)
					)
					((Said 'call/guard,man,gatekeeper')
						(if (== script guardSleeps)
							(HighPrint 38 10)
							;You're not close enough.  He's hard of hearing.
						else
							(HighPrint 38 14)
							;"I'm here!  What do you want?"
						)
					)
					((or (Said 'say') (Said '/hello'))
						(if (== script guardSleeps)
							(HighPrint 38 10)
							;You're not close enough.  He's hard of hearing.
						else
							(HighPrint 38 15)
							;"What?"
						)
					)
					((Said 'look/guard')
						(HighPrint 38 9)
						;The guard looks like he is either very tired or very lazy.
					)
					((Said 'chat[<to]/guard,man')
						(HighPrint 38 16)
						;Go ahead.
					)
					((Said 'chat,ask')
						(if (== script guardSleeps)
							(HighPrint 38 10)
							;You're not close enough.  He's hard of hearing.
						else
							(HighPrint 38 17)
							;"I'm not here to answer your stupid questions.
							;Go talk to Karl the gatekeeper.  He talks to anyone about almost anything."
						)
					)
				)
			)
		)
	)
)

(instance guardBody of View
	(properties
		y 135
		x 202
		view rCastleBarracks
	)
)

(instance onBarracks of RFeature
	(properties
		nsTop 86
		nsLeft 49
		nsBottom 170
		nsRight 211
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBarracks event shiftDown)
				(HighPrint 38 2)
				;The building's appearance is deceptive; it evidently extends well into the castle wall.
				;From your present vantage point, the barracks looks like it could house some sixty guards.
			)
		)
	)
)

(instance onFence of RFeature
	(properties
		nsTop 44
		nsLeft 286
		nsBottom 69
		nsRight MAXRIGHT
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFence event shiftDown)
				(HighPrint 38 18)
				;Just a fence.
			)
		)
	)
)

(instance onMountains of RFeature
	(properties
		nsTop 14
		nsLeft 28
		nsBottom 30
		nsRight 99
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onMountains event shiftDown)
				(HighPrint 38 19)
				;These are the mountains that ring the valley of Spielburg.
			)
		)
	)
)

(instance onTrees of RFeature
	(properties
		nsTop 30
		nsLeft 50
		nsBottom 39
		nsRight 80
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTrees event shiftDown)
				(HighPrint 38 20)
				;This is the forest that surrounds the castle.
			)
		)
	)
)

(instance onWall1 of RFeature
	(properties
		nsTop 8
		nsLeft 128
		nsBottom 36
		nsRight 318
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onWall1 event shiftDown)
					(MouseClaimed onWall2 event shiftDown)
					(MouseClaimed onWall3 event shiftDown)
				)
				(HighPrint 38 21)
				;The guards patrol from here.
			)
		)
	)
)

(instance onWall2 of RFeature
	(properties
		nsTop 40
		nsBottom 64
		nsRight 147
	)
)

(instance onWall3 of RFeature
	(properties
		nsTop 30
		nsBottom 190
		nsRight 48
	)
)

(instance guardSleeps of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guardHead
					setLoop: 1
					setPri: 10
					init:
					cycleSpeed: 6
					setCycle: Forward
				)
			)
		)
	)
)

(instance guardAwakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((< (ego y?) 140)
						(guardHead setLoop: 2 cel: 2 setCycle: 0)
					)
					((< (ego x?) 204)
						(guardHead setLoop: 2 cel: 1 setCycle: 0)
					)
					(else
						(guardHead setLoop: 2 cel: 0 setCycle: 0)
					)
				)
				(= cycles 3)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance guardThreatens of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guardHead cel: 0 setLoop: 3 cycleSpeed: 4 setCycle: Forward)
				(= cycles 20)
			)
			(1
				(HighPrint 38 22)
				;"Go away.  This is just the barracks for us guards."
				(= cycles 2)
			)
			(2
				(guardBody dispose:)
				(guardHead
					setLoop: 4
					cel: 0
					illegalBits: 0
					ignoreActors:
					x: 202
					y: 135
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(3
				(guardBody init:)
				(cond 
					((< (ego y?) 140)
						(guardHead
							setLoop: 2
							cel: 2
							x: 197
							y: 113
							cycleSpeed: 6
							setCycle: 0
						)
					)
					((< (ego x?) 204)
						(guardHead
							setLoop: 2
							cel: 1
							x: 197
							y: 113
							cycleSpeed: 6
							setCycle: 0
						)
					)
					(else
						(guardHead
							setLoop: 2
							cel: 0
							x: 197
							y: 113
							cycleSpeed: 6
							setCycle: 0
						)
					)
				)
				(= cycles 2)
			)
			(4
				(HandsOn)
				(self changeState: 3)
			)
		)
	)
)
