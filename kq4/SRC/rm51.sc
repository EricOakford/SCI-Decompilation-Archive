;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room51 0
)

(local
	aDoor
	aOgre
	aBody
	aHead
	aLegs
	aHen
	local6
	ogreSpeaks
)
(instance keyholeMusic of Sound
	(properties
		number 61
	)
)

(instance Room51 of Room
	(properties
		picture 51
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(= isIndoors TRUE)
		(= noWearCrown 1)
		(Load VIEW 2)
		(Load VIEW 614)
		(Load VIEW 647)
		(self setRegions: OGRE_HOUSE)
		(super init:)
		(keyholeMusic init:)
		(if (and (ego has: iMagicHen) (< ogreState 5))
			(= ogreState 4)
		)
		(if (or (== prevRoomNum 49) (== prevRoomNum 0))
			(ego
				view: 2
				setStep: 3 2
				posn: 156 139
				illegalBits: cWHITE
				init:
			)
			((= aDoor (Prop new:))
				view: 614
				loop: 0
				setCel: 255
				posn: 177 132
				ignoreActors: 0
				setPri: 8
				init:
				setCycle: BegLoop
			)
			((= aOgre (View new:))
				view: 614
				setLoop: 1
				posn: 165 105
				init:
				hide:
			)
			(if (and ogreState (< ogreState 5))
				(aOgre
					cel:
						(switch ogreState
							(1 0)
							(ogreEATING 1)
							(ogreSLEEPING (if (ego has: iMagicHen) 3 else 2))
							(4 0)
						)
					setPri: 2
					show:
				)
			)
			(ego loop: 2 setMotion: 0)
		)
	)
	
	(method (dispose)
		(= noWearCrown 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '/keyhole')
									(if (== (aDoor cel?) 0)
										(cond 
											((not ogreCameHome)
												(if (and (> ogreState 0) (!= ogreState 5))
													(self setScript: theKeyhole)
												else
													(self setScript: emptyHole)
												)
											)
											((!= script theKeyhole) (self setScript: emptyHole))
											(else (Print 51 0))
										)
									else
										(Print 51 1)
									)
								)
								((Said '/door') (Print 51 2))
								((Said '/wall') (Print 51 3))
								((or (Said '/dirt') (Said '<down')) (Print 51 4))
								((Said '[<around,at][/room,cottage,closet]') (Print 51 5))
							)
						)
						((Said 'open/door,closet')
							(if (== (aDoor cel?) 0)
								(if
								(or ogreCameHome (== ogreState 0) (== ogreState 5))
									(if (ego inRect: 134 131 169 141)
										(aDoor setScript: doorOpen)
									else
										(NotClose)
									)
								else
									(Print 51 6)
								)
							else
								(Print 51 7)
							)
						)
						((Said 'close/door')
							(if (aDoor cel?)
								(aDoor ignoreActors: 0 setCycle: BegLoop)
								(aOgre hide:)
							else
								(Print 51 8)
							)
						)
					)
				)
			)
		)
	)
)

(instance theKeyhole of Script
	(properties)
	
	(method (init param1)
		(Load VIEW 70)
		(Load VIEW 251)
		(Load VIEW 252)
		(Load VIEW 253)
		(Load VIEW 254)
		(Load VIEW 255)
		(Load VIEW 647)
		(Load PICTURE 52)
		((= aBody (Prop new:))
			view: 251
			loop: 0
			cel: 1
			posn: 152 89
			init:
			hide:
		)
		((= aHead (Prop new:))
			view: 251
			loop: 1
			posn: 175 65
			init:
			hide:
		)
		((= aLegs (View new:))
			view: 251
			loop: 0
			cel: 0
			posn: 162 115
			init:
			hide:
		)
		((= aHen (Prop new:))
			view: 253
			loop: 0
			cel: 0
			posn: 134 88
			init:
			hide:
		)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 144 134 self)
			)
			(1
				(ego view: 70 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(ego hide:)
				(aOgre hide:)
				(aDoor hide:)
				(curRoom drawPic: 52 15)
				(= ogreCameHome TRUE)
				(if isNightTime
					((View new:) view: 647 loop: 3 posn: 184 87 addToPic:)
				)
				(aBody show:)
				(aHead
					setPri: (+ (aBody priority?) 1)
					show:
					cycleSpeed: 1
					setCycle: Forward
				)
				(aLegs show: stopUpd:)
				(keyholeMusic play:)
				(= ogreSpeaks (Print 51 9 #font smallFont #at -1 5 #time 12))
				(= seconds 2)
			)
			(3
				(aHead setCycle: 0 stopUpd:)
				(Print 51 10
					#font smallFont
					#at -1 15
					#width 290
					#dispose
				)
				(aHead dispose:)
				(aBody
					view: 252
					loop: 0
					cel: 255
					posn: 152 88
					setCycle: EndLoop
				)
				(= seconds 2)
			)
			(4
				(aBody loop: 1 cel: 0 cycleSpeed: 2 setCycle: Forward)
				(= seconds 10)
			)
			(5
				(cls)
				(aBody loop: 1 cel: 0)
				(= seconds 2)
			)
			(6
				(aBody loop: 1 cel: 255 setCycle: EndLoop self)
			)
			(7
				(aBody loop: 0 cel: 0)
				(aHen show: setCycle: EndLoop)
				(= seconds 3)
			)
			(8
				(Print 51 11 #font smallFont #at -1 10 #time 10)
				(= seconds 3)
			)
			(9
				(aHen loop: 1 cel: 255 setCycle: EndLoop)
				((inventory at: 33) moveTo: 49)
				(= seconds 3)
			)
			(10
				(Print 51 12 #font smallFont #at -1 20 #draw #time 5)
				(Print 51 13 #font smallFont #at -1 15 #width 290 #dispose)
				(= seconds 5)
			)
			(11
				(aHen dispose:)
				(aBody
					view: 254
					loop: 0
					cel: 255
					ignoreActors: TRUE
					cycleSpeed: 2
					setCycle: EndLoop
				)
				(= seconds 5)
			)
			(12
				(aBody
					view: 255
					loop: 0
					cel: 255
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(13
				(aBody loop: 1 cel: 0 cycleSpeed: 2 setCycle: Forward)
				(= seconds 5)
			)
			(14
				(cls)
				(= ogreState ogreSLEEPING)
				(= seconds 5)
			)
			(15
				(aBody dispose:)
				(aLegs dispose:)
				(curRoom drawPic: 51 15)
				(HandsOn)
				(aDoor show:)
				(ego show: setCycle: BegLoop self)
			)
			(16
				(ego view: 2 loop: 3 setMotion: 0 setCycle: Walk)
				(HandsOn)
				(= seconds 60)
			)
			(17
				(Print 51 14)
				(= ogreState ogreLEAVING)
				(client setScript: 0)
			)
		)
	)
)

(instance emptyHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 144 134 self)
			)
			(1
				(ego view: 70 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(ego hide:)
				(aOgre hide:)
				(aDoor hide:)
				(curRoom drawPic: 52 15)
				(if isNightTime
					((View new:) view: 647 loop: 3 posn: 184 87 addToPic:)
				)
				(keyholeMusic play:)
				(if (== ogreState ogreSLEEPING)
					((= aBody (Prop new:))
						view: 255
						loop: 1
						cel: 0
						posn: 152 89
						init:
						cycleSpeed: 2
						setCycle: Forward
					)
					((= aLegs (View new:))
						view: 251
						loop: 0
						cel: 0
						posn: 162 115
						addToPic:
					)
					(= ogreSpeaks (Print 51 15 #at -1 10 #draw #dispose))
				)
				(= seconds 5)
			)
			(3
				(cls)
				(if (IsObject aBody) (aBody dispose:))
				(curRoom drawPic: 51 15)
				(aDoor show:)
				(ego view: 70 loop: 0 setCycle: BegLoop self show:)
			)
			(4
				(ego view: 2 loop: 3 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)

(instance doorOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (> ogreState 1) (< ogreState 5))
					(aOgre
						cel:
							(switch ogreState
								(ogreAWAY 0)
								(ogreEATING 1)
								(ogreSLEEPING (if (ego has: iMagicHen) 3 else 2))
								(ogreSTATE4 0)
							)
						setPri: 2
						show:
					)
				else
					(aOgre hide:)
				)
				(client ignoreActors: 1 setCycle: EndLoop self)
			)
			(1
				(RedrawCast)
				(HandsOn)
				(curRoom newRoom: 49)
			)
		)
	)
)
