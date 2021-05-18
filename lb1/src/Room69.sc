;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room69 0
)
(synonyms
	(butler fellow person)
	(room barn)
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
)
(instance Room69 of Rm
	(properties
		picture 69
	)
	
	(method (init)
		(super init:)
		(addToPics
			add: saddle trough bridle
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: saddle trough bridle hay1 hay2 window1 window2 window3
		)
		(LoadMany 132 40 85 118)
		(LoadMany 128 269 63)
		(if (ego has: 17) (Load rsVIEW 8))
		(myMusic number: 40 loop: 1)
		(if (and (not global121) (>= currentAct 2))
			(if (or (== currentAct 6) (== (Random 1 2) 1))
				(= global121 69)
			else
				(= global121 58)
			)
		)
		(if
			(and
				(== global121 69)
				(not (& deadGuests $0002))
				(not (& deadGuests $0040))
			)
			(self setRegions: 256)
		)
		(Door init: ignoreActors: 1 stopUpd:)
		(if (== ((inventory at: 2) owner?) 69)
			(Lamp init: stopUpd:)
		)
		(if
			(and
				(== currentAct 1)
				(or (== global155 6) (== global155 7))
			)
			(Jeeves init:)
			(HandsOff)
			(self setScript: feedNag)
		else
			(HandsOn)
		)
		(Horse setPri: 9 init: stopUpd:)
		(Ears setPri: 9 init:)
		(Leg setPri: 9 init: stopUpd:)
		(Tail setPri: 9 init: stopUpd:)
		(Head setPri: 9 init: stopUpd:)
		(ego view: 0 posn: 211 170 illegalBits: -32752 init:)
		(if (== currentAct 1)
			(Load rsVIEW 440)
			(Load rsVIEW 445)
			(Load rsVIEW 452)
			(Hay init:)
		)
	)
	
	(method (doit &tmp temp0)
		(if (FirstEntry) (Print 69 0))
		(if
		(and local0 (& (ego onControl: 1) $0001) (not script))
			(Room69 setScript: myDoor)
		)
		(if
		(and (== [global368 2] 1100) (== currentAct 1))
			(if (not script)
				(= local4 1)
				(HandsOff)
				(self setScript: feedNag)
			else
				(= [global368 2] 1110)
			)
		)
		(if (not local1)
			(cond 
				((== (= temp0 (Random 1 25)) 1)
					(cond 
						((== (Ears cel?) 0) (Ears setCycle: End))
						((== (Ears cel?) (- (NumCels Ears) 1)) (Ears setCycle: Beg))
					)
				)
				((and howFast (> temp0 2) (< temp0 6))
					(cond 
						((== (Tail cel?) 0) (Tail setCycle: End))
						((== (Tail cel?) (- (NumCels Tail) 1)) (Tail setCycle: Beg))
					)
				)
				((and howFast (> temp0 5) (< temp0 8))
					(cond 
						((== (Leg cel?) 0) (Leg setCycle: End))
						((== (Leg cel?) (- (NumCels Leg) 1)) (Leg setCycle: Beg))
					)
				)
			)
		)
		(if (and (not local1) (not local3))
			(= local3 (Random 30 80))
			(myMusic number: 40 loop: 1 play:)
		)
		(-- local3)
		(if (& (ego onControl: 0) $0002)
			(HandsOff)
			(curRoom newRoom: 13)
		)
		(if
			(and
				(< (ego distanceTo: Horse) 65)
				(> (ego y?) 140)
				(!= ((inventory at: 17) owner?) 69)
				(== local0 1)
				(== local1 0)
			)
			(= local1 1)
			(self setScript: kicked)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]') (Print 69 0))
							((Said '/door') (Print 69 1))
							((Said '/room')
								(if (== ((inventory at: 2) owner?) 69)
									(Print 69 2)
								else
									(Print 69 3)
								)
							)
							((or (Said '/dirt,dirt') (Said '<down')) (Print 69 4))
							((Said '/wall')
								(if (== ((inventory at: 2) owner?) 69)
									(Print 69 5)
								else
									(Print 69 6)
								)
							)
							((or (Said '/ceiling') (Said '<up')) (Print 69 7))
						)
					)
					((Said 'feel,get/lantern/cane') (if (ego has: 21) (Print 69 8) else (DontHave)))
					((Said 'climb/wall,room') (Print 69 9))
					((Said 'open/window') (Print 69 10))
					((Said 'break/window') (Print 69 11))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (cast contains: Jeeves)
			(= [global368 2] 1050)
			(= global155 7)
		)
		(super newRoom: n)
	)
)

(instance feedHorse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 150 138 self
				)
			)
			(1
				(Face ego Horse)
				(ego
					view: 8
					cel: 0
					loop: 0
					setAvoider: 0
					setCycle: End self
				)
			)
			(2
				(Print 69 12)
				(ego cel: 0 loop: 1 setCycle: End self)
			)
			(3
				(ego cel: 0 loop: 2 setCycle: End self)
				(Mouth setPri: 9 cycleSpeed: 3 setCycle: Fwd init:)
			)
			(4
				(ego put: 17 69)
				(ego view: 0 loop: 1 setCycle: Walk)
				(HandsOn)
				(= seconds 5)
			)
			(5
				(Mouth dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: 0
					setAvoider: (Avoid new:)
					setMotion: MoveTo 140 142 self
				)
			)
			(1
				(Face ego Horse)
				(if (== local0 0)
					(= local0 1)
					(Door setPri: 13 cycleSpeed: 3 setCycle: End self)
					(ego ignoreControl: 16 observeControl: 32)
				else
					(= local0 0)
					(Door setPri: 10 cycleSpeed: 3 setCycle: Beg self)
					(ego ignoreControl: 32 observeControl: 16)
				)
			)
			(2
				(ego setAvoider: 0)
				(Door stopUpd:)
				(if (!= ((inventory at: 17) owner?) 69)
					(Room69 setScript: kicked)
				else
					(HandsOn)
					(client setScript: 0)
				)
			)
		)
	)
)

(instance kicked of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 1) (== local2 0) (== (Horse cel?) 4))
			(= local2 1)
			(ego
				view: 269
				setLoop: 1
				cel: 0
				illegalBits: 0
				cycleSpeed: 1
				setCycle: End
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 130 142 self)
			)
			(1
				(HandsOff)
				(Tail hide:)
				(Leg hide:)
				(Head hide:)
				(Ears hide:)
				(Horse
					view: 269
					loop: 0
					cel: 0
					cycleSpeed: 1
					setPri: 12
					startUpd:
					setCycle: End self
				)
				(myMusic number: 85 loop: 1 play:)
			)
			(2
				(Horse setPri: 9 stopUpd:)
				(= cycles 21)
			)
			(3
				(= cIcon 269)
				(= deathLoop 2)
				(= deathCel 2)
				(= global127 1)
				(EgoDead 69 13)
				(client setScript: 0)
			)
		)
	)
)

(instance feedNag of Script
	(properties)
	
	(method (doit)
		(if (and (== state 3) (== (Jeeves cel?) 3))
			(Hay posn: 104 145 setPri: 9 setCycle: End show:)
		)
		(if (and (== state 1) (== (Jeeves cel?) 4))
			(Hay hide:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego inRect: 109 138 200 150)
					(ego setMotion: MoveTo 160 135)
				)
				(if (ego inRect: 200 139 259 150)
					(ego setMotion: MoveTo 259 143)
				)
				(if (ego inRect: 204 151 263 200)
					(ego setMotion: MoveTo 189 153)
				)
				(if local4
					(Jeeves
						view: 440
						posn: 220 170
						setCycle: Walk
						setMotion: MoveTo 238 145 self
						init:
					)
				else
					(= cycles 1)
				)
			)
			(1
				(Jeeves view: 445 loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(Jeeves
					view: 452
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 124 145 self
				)
			)
			(3
				(Jeeves view: 445 setLoop: 2 cel: 0 setCycle: End self)
			)
			(4
				(Jeeves
					view: 440
					setLoop: -1
					setCycle: Walk
					setAvoider: (Avoid new:)
					setMotion: MoveTo 193 145 self
				)
				(if (ego inRect: 200 162 227 176)
					(ego setMotion: MoveTo 186 170)
				)
			)
			(5
				(Jeeves setMotion: MoveTo 220 170 self)
			)
			(6
				(HandsOn)
				(= global155 8)
				(= [global368 2] (- 1700 (* global155 100)))
				(Jeeves dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance petHorse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: (Avoid new:)
					setMotion: MoveTo 145 138 self
				)
			)
			(1
				(Face ego Horse)
				(ego
					view: 63
					cel: 0
					loop: 0
					setAvoider: 0
					setCycle: End self
				)
			)
			(2
				(ego cel: 0 loop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(3
				(if (== ((inventory at: 17) owner?) 69)
					(Print 69 14)
				else
					(Print 69 15)
					(Ears setCycle: End)
				)
				(ego cel: 0 loop: 2 setCycle: End self)
			)
			(4
				(ego view: 0 loop: 1 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance saddle of RPicView
	(properties
		y 109
		x 268
		view 169
		loop 1
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'detach,use,get/saddle')
					(Said 'attach/saddle')
					(Said 'saddle/blaze')
				)
				(Print 69 16)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/saddle')) (event claimed: 1) (Print 69 17))
		)
	)
)

(instance trough of RPicView
	(properties
		y 153
		x 73
		view 169
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine/trough,water')
			)
			(event claimed: 1)
			(Print 69 18)
		)
	)
)

(instance bridle of RPicView
	(properties
		y 96
		x 142
		view 169
		loop 1
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'detach,use,get/bit') (Print 69 19))
			(
			(or (MousedOn self event 3) (Said 'examine/bit')) (event claimed: 1) (Print 69 20))
		)
	)
)

(instance Door of Prop
	(properties
		y 147
		x 100
		view 169
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'enter,(go<in)/room,archway') (Print 69 21))
			((Said 'open,enter,(go<in)/room,archway')
				(if (& (ego onControl: 0) $0004)
					(if (== local0 0)
						(Room69 setScript: myDoor)
					else
						(AlreadyOpen)
					)
				else
					(Print 69 22)
				)
			)
			((Said 'close/archway,room')
				(if (== local0 1)
					(Room69 setScript: myDoor)
				else
					(AlreadyClosed)
				)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/archway,room')
				)
				(event claimed: 1)
				(Print 69 23)
			)
		)
	)
)

(instance Lamp of Prop
	(properties
		y 110
		x 42
		view 169
		loop 1
		priority 7
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/lantern,lamp,ignite')
				)
				(event claimed: 1)
				(Print 69 5)
			)
			((Said 'get/lantern,ignite')
				(if (< (ego distanceTo: Lamp) 45)
					(Lamp dispose:)
					(= gotItem 1)
					(ego get: 2)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance Hay of Prop
	(properties
		y 141
		x 228
		view 445
		loop 3
		cel 4
	)
)

(instance Leg of Prop
	(properties
		y 141
		x 104
		view 169
		loop 4
		signal $4000
		cycleSpeed 2
	)
)

(instance Tail of Prop
	(properties
		y 104
		x 62
		view 169
		loop 3
		signal $4000
		cycleSpeed 2
	)
)

(instance Ears of Prop
	(properties
		y 94
		x 119
		view 169
		loop 5
		signal $4000
	)
)

(instance Mouth of Prop
	(properties
		y 105
		x 105
		view 8
		loop 3
	)
)

(instance Head of Prop
	(properties
		y 108
		x 99
		view 169
		loop 2
		cel 1
	)
)

(instance Horse of Prop
	(properties
		y 140
		x 51
		view 169
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/blaze')) (event claimed: 1) (Print 69 24))
			(
			(or (Said 'bit/blaze') (Said 'attach,attach/bit/blaze')) (Print 69 25))
			((Said '(feed,deliver,hold)>')
				(cond 
					((Said '/carrot')
						(cond 
							((== ((inventory at: 17) owner?) 69) (Print 69 26))
							((ego has: 17)
								(if (& (ego onControl: 0) $0004)
									(Room69 setScript: feedHorse)
								else
									(NotClose)
								)
							)
							(else (DontHave))
						)
					)
					((Said '/(bale[<blaze]),blaze') (Print 69 27))
					((Said '/food') (Print 69 28))
					((Said '/*')
						(event claimed: 1)
						(if (and theInvItem haveInvItem)
							(Print 69 29)
						else
							(DontHave)
						)
					)
				)
			)
			((Said 'ask[/blaze]/c<about') (myEd number: 118 loop: 1 play:) (Print 69 30))
			((Said 'ask,tell[/blaze]/*<about') (Print 69 31))
			((Said '/blaze>')
				(cond 
					((Said 'converse') (Print 69 32))
					((Said '(get<on),mount,mount,climb') (Print 69 33))
					((Said 'guide,move,press,get,get')
						(if (== ((inventory at: 17) owner?) 69)
							(Print 69 34)
						else
							(Print 69 21)
						)
					)
					((Said 'feed,deliver/carrot')
						(cond 
							((== ((inventory at: 17) owner?) 69) (Print 69 26))
							((ego has: 17)
								(if (& (ego onControl: 0) $0004)
									(Room69 setScript: feedHorse)
								else
									(NotClose)
								)
							)
							(else (Print 69 28))
						)
					)
					((Said 'call') (Print 69 35))
					((Said 'kiss')
						(if (== ((inventory at: 17) owner?) 69)
							(Print 69 36)
						else
							(Print 69 37)
						)
					)
					((Said 'kill') (Print 69 38))
					((Said 'pat,pat')
						(if (< (ego distanceTo: Horse) 105)
							(Room69 setScript: petHorse)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance Jeeves of Act
	(properties
		y 145
		x 238
		view 440
	)
	
	(method (handleEvent)
		(cond 
			((Said 'examine/butler') (Print 69 39))
			((Said '*/butler') (Print 69 40))
		)
	)
)

(instance myMusic of Sound
	(properties)
)

(instance myEd of Sound
	(properties)
)

(instance hay1 of RFeature
	(properties
		nsTop 145
		nsLeft 137
		nsBottom 163
		nsRight 169
	)
	
	(method (handleEvent event)
		(cond 
			((or (Said 'drink[/water]') (Said 'get/drink')) (Print 69 41))
			((Said 'get/water') (Print 69 42))
			((Said 'examine<in/water,trough') (Print 69 43))
			((Said 'get/bale') (Print 69 44))
			((Said 'search,(examine<in)/bale')
				(if (ego inRect: 193 128 241 142)
					(Print 69 45)
				else
					(NotClose)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/bale')) (event claimed: 1) (Print 69 46))
		)
	)
)

(instance hay2 of RFeature
	(properties
		nsTop 118
		nsLeft 207
		nsBottom 135
		nsRight 238
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 69 46)
		)
	)
)

(instance window1 of RFeature
	(properties
		nsTop 62
		nsLeft 171
		nsBottom 81
		nsRight 198
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event 3) (Said '/window'))
			(event claimed: 1)
			(Print 69 47)
		)
	)
)

(instance window2 of RFeature
	(properties
		nsTop 69
		nsLeft 213
		nsBottom 87
		nsRight 241
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 69 47)
		)
	)
)

(instance window3 of RFeature
	(properties
		nsTop 8
		nsLeft 69
		nsBottom 26
		nsRight 95
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 69 47)
		)
	)
)
