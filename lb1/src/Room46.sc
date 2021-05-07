;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room46 0
)
(synonyms
	(painting blaze)
	(luggage case bag)
	(drawer dresser)
	(room bedroom)
)

(local
	local0
	local1
	local2
)
(instance myMusic of Sound
	(properties)
)

(instance Room46 of Rm
	(properties
		picture 46
	)
	
	(method (init)
		(= east 47)
		(super init:)
		(addToPics
			add: suit1 suit2 horse bed1 bed2 chest table2 sofa
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures: suit1 suit2 horse table2 bed1 bed2 sofa chest
		)
		(table
			cel:
			(switch currentAct
				(1 9)
				(else  8)
			)
			setPri: 6
			init:
			stopUpd:
		)
		(if (== global177 2) (table cel: 8))
		(if (and (== currentAct 1) (!= global177 2))
			(plant setPri: 6 init: stopUpd:)
		)
		(if howFast
			(lamp1 setPri: 6 setCycle: Fwd init:)
			(lamp2 setPri: 6 setCycle: Fwd init:)
		else
			(lamp1 setPri: 6 init: stopUpd:)
			(lamp2 setPri: 6 init: stopUpd:)
		)
		(Bwindow ignoreActors: 1 setPri: 7 init: stopUpd:)
		(panel
			setLoop: 1
			setCel: 11
			illegalBits: 0
			setPri: 4
			init:
			stopUpd:
		)
		(LoadMany 132 74 75)
		(switch currentAct
			(0
				(if (& global173 $0002)
					(self setRegions: 224)
					(= local0 1)
				)
				(= global177 0)
			)
			(1
				(if (< global177 2)
					(= local1 1)
					(Bwindow
						view: 246
						setPri: 9
						loop: 2
						cel: 0
						posn: 37 127
						cycleSpeed: 2
						ignoreActors: 0
						setCycle: Fwd
					)
					(Twindow
						cycleSpeed: 2
						cel: (- (NumCels Bwindow) 2)
						setCycle: Fwd
						init:
					)
					(myMusic number: 12 loop: -1 play:)
				)
			)
		)
		(if (!= prevRoomNum 49)
			(ego view: 0 illegalBits: -32764 posn: 304 101 init:)
		else
			(ego
				view: 0
				illegalBits: -32768
				setPri: 2
				loop: 2
				posn: 152 79
				init:
			)
			(if (== local0 0)
				(ego posn: 152 79)
				(self setScript: enterPanel)
			)
		)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 46 0))
		(if (and (== currentAct 1) (== global177 0))
			(Printf 46 1 46 2)
			(Print 46 3)
			(= global177 1)
			(= local2 1)
		)
		(if
			(or
				(ego inRect: 60 80 116 104)
				(ego inRect: 178 82 213 105)
			)
			(ego setPri: 5)
		else
			(ego setPri: -1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine,find/gertie,body')
						(if (== currentAct 0)
							(event claimed: 0)
						else
							(Print 46 4)
						)
					)
					((Said '/panel,(door<hidden)>')
						(cond 
							((and (& global175 $0040) (Said 'open,move'))
								(if (not local0)
									(if (& (ego onControl: 0) $0010)
										(HandsOff)
										(self setScript: exiting)
									else
										(NotClose)
									)
								else
									(Print 46 5)
								)
							)
							((Said 'examine') (if (& global175 $0040) (Print 46 6) else (Print 46 7)))
						)
					)
					((Said 'examine[<around,at][/room]')
						(if (and (== currentAct 1) local1)
							(Printf 46 1 46 2)
							(Print 46 3)
						else
							(Print 46 0)
						)
					)
					((Said 'smell/smoke,butt')
						(if (and (== currentAct 1) local2)
							(Print 46 8)
						else
							(event claimed: 0)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance enterPanel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global204 1)
				(panel setMotion: MoveTo 180 84 self)
				(myMusic number: 74 loop: 1 play:)
			)
			(1
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15) self)
			)
			(2
				(ego setPri: -1 illegalBits: -32764)
				(panel setMotion: MoveTo 155 84 self)
				(myMusic number: 75 loop: 1 play:)
			)
			(3
				(HandsOn)
				(Print 46 9)
				(= global204 0)
				(client setScript: 0)
			)
		)
	)
)

(instance exiting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global204 1)
				(panel setMotion: MoveTo 180 84 self)
				(myMusic number: 74 loop: 1 play:)
			)
			(1
				(if (ego inRect: 150 87 154 90)
					(= cycles 1)
				else
					(ego illegalBits: -32768 setMotion: MoveTo 152 85 self)
				)
			)
			(2
				(ego illegalBits: -32768 setMotion: MoveTo 154 79 self)
			)
			(3
				(ego setPri: 2)
				(panel setMotion: MoveTo 155 84 self)
				(myMusic number: 75 loop: 1 play:)
			)
			(4
				(HandsOn)
				(= global204 0)
				(curRoom newRoom: 49)
				(client setScript: 0)
			)
		)
	)
)

(instance closeWindow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 75 120 self)
			)
			(1
				(ego loop: 1)
				(Twindow hide:)
				(Bwindow
					posn: 37 127
					loop: 0
					cel: 3
					setPri: 7
					setCycle: Beg self
				)
			)
			(2
				(myMusic stop:)
				(Bwindow ignoreActors: 1 setPri: 7 stopUpd:)
				(= local1 0)
				(= global177 3)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance horse of RPicView
	(properties
		y 53
		x 183
		view 146
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind,below/painting') (Print 46 10))
			((Said 'get/painting') (Print 46 11))
			((Said 'open/painting') (Print 46 12))
			((Said 'examine/eye,(painting<eye)') (Print 46 13))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/painting')
					(Said 'examine/painting/painting')
				)
				(event claimed: 1)
				(Print 46 14)
			)
		)
	)
)

(instance bed1 of RPicView
	(properties
		y 106
		x 110
		view 146
		cel 4
		priority 6
		signal $4000
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 46 15)
		)
	)
)

(instance chest of RPicView
	(properties
		y 93
		x 182
		view 146
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine[<!*]/drawer')
			)
			(event claimed: 1)
			(Print 46 16)
		)
	)
)

(instance bed2 of RPicView
	(properties
		y 107
		x 215
		view 146
		cel 5
		priority 6
		signal $4000
	)
	
	(method (handleEvent event)
		(if (and (not local0) (MousedOn self event 3))
			(event claimed: 1)
			(ParseName {bed})
		)
	)
)

(instance table2 of RPicView
	(properties
		y 94
		x 256
		view 146
		cel 2
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {table})
		)
	)
)

(instance sofa of RPicView
	(properties
		y 157
		x 133
		view 146
		cel 7
		priority 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {couch})
		)
	)
)

(instance suit1 of RPicView
	(properties
		y 119
		x 211
		view 146
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,(examine<in)/luggage') (Print 46 17))
			((Said 'get/luggage') (Print 46 18))
			(
			(or (MousedOn self event 3) (Said 'examine/luggage')) (event claimed: 1) (Print 46 19))
		)
	)
)

(instance suit2 of RPicView
	(properties
		y 86
		x 106
		view 146
		cel 6
		priority 7
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 46 19)
		)
	)
)

(instance Twindow of Prop
	(properties
		y 37
		x 60
		view 246
		loop 1
	)
)

(instance Bwindow of Prop
	(properties
		y 127
		x 37
		view 246
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine/curtain') (if local1 (Print 46 20) else (event claimed: 0)))
			((Said '(go,climb,hop)<out/window') (if local1 (Print 46 21) else (Print 46 22)))
			((Said 'open/window') (if local1 (Print 46 23) else (Print 46 24)))
			((Said 'close/window')
				(if local1
					(if (& (ego onControl: 0) $0008)
						(self setScript: closeWindow)
					else
						(NotClose)
					)
				else
					(Print 46 25)
				)
			)
			(
				(or
					(Said 'examine[<down]/dirt')
					(Said 'examine<(down,out)')
					(Said 'examine<(out,through)/window')
				)
				(if local1
					(if (& (ego onControl: 0) $0008)
						(Print 46 26)
					else
						(NotClose)
					)
				else
					(event claimed: 0)
				)
			)
			((MousedOn self event 3)
				(event claimed: 1)
				(if local1 (Print 46 27) else (ParseName {window}))
			)
			((Said 'examine/window') (if local1 (Print 46 27) else (event claimed: 0)))
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 45
		x 120
		view 148
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 46
		x 250
		view 148
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance panel of Act
	(properties
		y 84
		x 155
		view 146
	)
)

(instance table of Prop
	(properties
		y 111
		x 72
		view 146
	)
	
	(method (handleEvent event)
		(cond 
			((and (== currentAct 1) (!= global177 2))
				(cond 
					(
					(or (Said 'examine/dirt') (Said 'examine<down')) (Print 46 28))
					((Said 'get,straighten/nightstand,foliage') (Print 46 29))
					(
						(or
							(Said 'examine/nightstand,foliage')
							(MousedOn self event 3)
						)
						(Print 46 30)
						(event claimed: 1)
					)
				)
			)
			(
			(or (Said 'examine/foliage') (MousedOn self event 3)) (Print 46 31))
		)
	)
)

(instance plant of Prop
	(properties
		y 109
		x 99
		view 146
		cel 10
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/foliage'))
			(event claimed: 1)
			(Print 46 32)
		)
	)
)
