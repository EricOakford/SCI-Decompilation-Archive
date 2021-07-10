;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include sci.sh)
(use Main)
(use Intrface)
(use tahiti)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use Grooper)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	inHerHut 0
)
(synonyms
	(kiss embrace squeeze hug)
)

(local
	local0
	local1
	local2
)
(instance inHerHut of Rm
	(properties
		picture 14
		west 13
		vanishingY 5
	)
	
	(method (init)
		(super init:)
		(Load rsSOUND 42)
		(Load rsSOUND 36)
		(LoadMany 128 206 212 14 200 208 106 114 314 2)
		(LoadMany 132 14 29)
		(door
			init:
			ignoreActors: 1
			setCel: 16
			setPri: 0
			setCycle: Beg door
		)
		(Lamp init:)
		(Couch init:)
		(Carpet init:)
		(GlassTable init:)
		(flowersOnTable init:)
		(Plant init:)
		(Plant2 init:)
		(addToPics doit:)
		(InitAllFeatures)
		((= local0 (ScriptID 309 0))
			init:
			view: 212
			posn: 55 139
			loop: 0
			setLoop: -1
			setPri: -1
			setCycle: Walk
			setScript: agentScript
		)
		(ego
			view: 206
			posn: 55 142
			loop: 0
			setLoop: -1
			observeControl: 16384
			init:
		)
		(self
			setRegions: 300
			setFeatures:
				Bed
				rugFeature
				windowFeature
				closetFeat
				((Clone windowFeature)
					x: 245
					nsLeft: 230
					nsRight: 260
					yourself:
				)
				((Clone windowFeature)
					y: 40
					x: 160
					nsBottom: 55
					nsLeft: 100
					nsRight: 215
					yourself:
				)
		)
		(smoochSong number: (SoundFX 14) loop: -1 play:)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((super handleEvent: event))
			((Said 'look/bush') (Print 14 0))
			((Said 'look[<around,at][/room][/!*]')
				(if (cast contains: local0)
					(switch (Random 0 2)
						(0 (Print 14 1))
						(1 (Print 14 2))
						(2 (Print 14 3))
					)
				else
					(Print 14 4)
					(Print 14 5)
				)
			)
			((Said 'look[<at]//earring') (Print 14 6))
			((Said 'call<use') (Print 14 7))
			((Said 'kiss')
				(if (cast contains: local0)
					(Print 14 8)
				else
					(Print 14 9)
				)
			)
			((Said '(stand[<up]),(get<up)') (Print 14 10))
			((Said 'detach/clothes,shirt')
				(if (cast contains: local0)
					(Print 14 11)
				else
					(Print 14 12)
				)
			)
		)
	)
)

(instance Plant of RPicView
	(properties
		y 161
		x 287
		view 14
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/plant][/floor,corner]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 13))
					((Said 'move') (DontNeedTo))
				)
			)
		)
	)
)

(instance Plant2 of RPicView
	(properties
		y 83
		x 219
		view 14
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/plant][/floor,corner]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 13))
					((Said 'move') (DontNeedTo))
				)
			)
		)
	)
)

(instance Lamp of RPicView
	(properties
		y 67
		x 93
		view 14
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/lamp][/floor,table]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 14))
					((Said 'turn[<on,off]') (DontNeedTo))
				)
			)
		)
	)
)

(instance flowersOnTable of RPicView
	(properties
		y 77
		x 170
		view 14
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table,desk,flower][/table,desk]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<on,at]')
						(Print 14 15)
						(if (and (cast contains: note) (not (note z?)))
							(Print 14 16)
						)
					)
					((Said 'look<below') (Print 14 17))
					((GoToIfSaid self event self 25 'smell' 14 18))
					((Said 'smell') (Print 14 19))
				)
			)
			((Said '[/drawer]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 20))
					((Said 'open') (Print 14 21))
				)
			)
		)
	)
)

(instance Carpet of PV
	(properties
		y 112
		x 204
		view 14
		cel 2
		priority 0
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/carpet,carpet][/floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,on]') (Print 14 22))
					((GoToIfSaid self event self 5 0 14 18))
					((Said 'look[<below]') (Print 14 23))
				)
			)
		)
	)
)

(instance rugFeature of RFeature
	(properties
		y 145
		x 75
		nsTop 135
		nsLeft 50
		nsBottom 155
		nsRight 100
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/carpet,carpet][/floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 22))
					((GoToIfSaid self event self 5 0 14 18))
					((Said 'look[<below]') (Print 14 23))
				)
			)
		)
	)
)

(instance GlassTable of RPicView
	(properties
		y 112
		x 204
		view 14
		cel 3
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/carpet,(table[<coffee])][/floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<on,at]') (Print 14 24))
				)
			)
		)
	)
)

(instance Couch of RPicView
	(properties
		y 114
		x 237
		view 14
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/couch][/floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 25))
					((Said 'look<below') (Print 14 26))
					((GoToIfSaid self event 220 115 'sit[<on]' 14 18))
					((Said 'sit[<on]') (ego setScript: sitScript))
				)
			)
		)
	)
)

(instance sitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreControl: -32768 setMotion: MoveTo 225 111 self)
			)
			(1
				(ego heading: 270)
				((ego looper?) doit: ego 270)
				(= cycles 8)
			)
			(2
				(ego view: 106 loop: 6 setCel: 0 setCycle: End self)
			)
			(3 (User canInput: 1))
			(4
				(User canInput: 0)
				(ego setCycle: Beg self)
			)
			(5
				(ego
					view: 206
					loop: 1
					setCycle: Walk
					observeControl: -32768
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(and (Said '(stand[<up]),(get<up)') (== state 3)) (= cycles 1))
		)
	)
)

(instance Bed of RFeature
	(properties
		y 80
		x 111
		nsTop 65
		nsLeft 81
		nsBottom 105
		nsRight 141
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bed][/floor]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 27))
					(
					(GoToIfSaid self event self 30 'look[<below]' 14 18))
					((Said 'look[<below]') (Print 14 28))
					((Said 'make/') (Print 14 29))
					((Said 'look[<in]') (Print 14 30))
					((Said 'sit[/*]')
						(if (== (ego view?) 206)
							(ego setScript: egoSitScript)
						else
							(DontNeedTo)
						)
					)
				)
			)
			((Said 'look/pillow') (SeeNothing))
		)
	)
)

(instance agentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(local0
					ignoreControl: -32768
					setMotion: MoveTo 117 106 self
				)
				(theSound number: (SoundFX 42) loop: 1 play:)
			)
			(1
				(local0 setLoop: 2)
				(= cycles 1)
			)
			(2
				(local0 view: 14 setLoop: 4 setCycle: End self)
			)
			(3
				(HandsOn)
				(Print 14 31 #at 70 150)
			)
			(4)
			(5
				(switch (Random 0 1)
					(0 (Print 14 32 #at 70 150))
					(1 (Print 14 33 #at 70 150))
				)
			)
			(6 (Print 14 34 #at 70 150))
			(7
				(switch (Random 4 5)
					(4 (Print 14 35 #at 70 150))
					(5 (Print 14 36 #at 70 150))
				)
			)
			(8 (Print 14 37 #at 70 150))
			(9
				(ego
					observeControl: -32768
					ignoreActors: 0
					setLoop: -1
					setScript: 0
				)
				(ego hide:)
				(door hide:)
				(local0 dispose:)
				(HandsOff)
				(theGame changeScore: 6)
				(curRoom drawPic: 99 setScript: messageSexWithAgentScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address')
				(if (== (ego view?) 206)
					(Print 14 38)
				else
					(self setScript: talkScript)
				)
			)
			((Said 'love<i/ya')
				(switch (Random 0 2)
					(0 (Print 14 39))
					(1 (Print 14 40))
					(2 (Print 14 41))
				)
			)
			((Said 'sit[<on][/bed]')
				(if (== (ego view?) 106)
					(Print 14 42)
				else
					(if (== state 3) (self cue:))
					(ego setScript: egoSitScript)
				)
			)
			(
				(and
					(!= (ego view?) 106)
					(== state 3)
					(Said 'affirmative')
				)
				(if (== state 3) (self cue:))
				(ego setScript: egoSitScript)
			)
			(
				(Said
					'feel,touch,rest,fuck,undress,fondle,make[/babe,love,stacy,boob]'
				)
				(switch (Random 0 2)
					(0 (Print 14 43))
					(1 (Print 14 44))
					(2 (Print 14 45))
				)
			)
		)
	)
)

(instance talkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(register (Print 14 46) (self dispose:))
					((== (ego view?) 106) (Print 14 47))
					(else (ego setLoop: 8 ignoreActors: 1 setCycle: End self))
				)
			)
			(1
				(Print 14 48)
				(Print 14 49)
				(Print 14 50)
				(Print 14 51)
				(Print 14 52)
				(= register 1)
				(tahiti flags: (| (tahiti flags?) $0400))
				(self dispose:)
			)
		)
	)
)

(instance egoSitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego ignoreActors: setAvoider: Avoid)
				(HandsOff)
				(if
					(and
						(< (ego y?) (+ 5 (local0 y?)))
						(< (- (local0 x?) 28) (ego x?))
						(< (ego x?) (local0 x?))
					)
					(= cycles 1)
				else
					(ego
						setMotion: MoveTo (- (local0 x?) 14) (+ 5 (local0 y?)) self
					)
				)
			)
			(1
				(ego
					ignoreControl: -32768
					setMotion: MoveTo (- (local0 x?) 14) (local0 y?) self
				)
			)
			(2
				(ego setLoop: 2)
				(= cycles 3)
			)
			(3
				(ego
					view: 14
					setLoop: 3
					ignoreControl: -32768
					setCycle: End self
				)
			)
			(4
				(local0 z: 1000)
				(ego view: 14 heading: 180)
				(if (cast contains: local0)
					(ego setLoop: 5 cel: 0 setScript: kissScript)
				else
					(ego setLoop: 3 cel: 2)
				)
				(HandsOn)
				(User canControl: 0)
				(ego setAvoider: 0 ignoreActors: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit') (Print 14 53))
			((Said '(stand[<up]),(get<up)') (ego setScript: egoStandScript))
		)
	)
)

(instance Mushroom of Prop
	(properties
		view 14
		loop 6
	)
	
	(method (init)
		(super init:)
		(self
			posn: (+ 10 (ego x?)) (- (ego y?) 40)
			setCycle: End self
		)
	)
	
	(method (cue)
		(kissScript cue:)
		(self dispose:)
	)
)

(instance kissScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if (OneOf (ego loop?) 9 8)
					(ego setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(2
				(HandsOff)
				(ego setLoop: 5 setCycle: End self)
			)
			(3 (Mushroom init:))
			(4 (ego setCycle: Beg self))
			(5
				(if (Random 0 1)
					(ego setLoop: 9 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(6
				(HandsOn)
				(User canControl: 0)
				(local0 cue:)
				(if client (self init:))
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit') (Print 14 53 #at -1 100))
			((Said 'kiss') (ego cue:))
			((Said '(stand[<up]),(get<up)') (ego setScript: egoStandScript))
		)
	)
)

(instance egoStandScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(local0 z: 0)
				(ego setLoop: 3 setCel: 16 setCycle: Beg self)
			)
			(1
				(ego
					view: 206
					setLoop: -1
					setCycle: Walk
					observeControl: -16384
					loop: 2
					posn: (ego x?) (+ (ego y?) 2)
				)
				(HandsOn)
			)
		)
	)
)

(instance messageSexWithAgentScript of Script
	(properties)
	
	(method (init)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 14 54 #dispose)
				(tahiti flags: (| (tahiti flags?) $0040))
				(= global142 1)
				(Load rsSOUND 29)
				(self setScript: fireWorkScript)
			)
			(1
				(fireWorkScript dispose:)
				(cls)
				(smoochSong client: self fade: 1)
			)
			(2
				(smoochSong number: 29 loop: -1 play:)
				(HandsOn)
				(User canControl: 0)
				(curRoom drawPic: 14)
				(door show:)
				(Lamp init:)
				(Couch init:)
				(Carpet init:)
				(GlassTable init:)
				(flowersOnTable init:)
				(Plant init:)
				(Plant2 init:)
				(bed2 init:)
				(note init:)
				(shirt init: setPri: 4)
				(messedUpBed init:)
				(addToPics doit:)
				(curRoom setFeatures: messedUpBed)
				(sandals init: ignoreActors:)
				(ego
					show:
					ignoreControl: -32768
					view: 314
					loop: 0
					cel: 0
					posn: 144 81
					setPri: 14
				)
			)
			(3
				(bed2 addToPic:)
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(4
				(sandals dispose:)
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(ego
					view: 200
					loop: 0
					heading: 90
					setLoop: -1
					setPri: -1
					setCycle: Walk
					setMotion: MoveTo 179 80 self
				)
			)
			(6
				(shirt hide:)
				(ego view: 208 loop: 0 setCycle: End self)
			)
			(7
				(ego
					observeControl: -32768
					view: 206
					setCycle: Walk
					setLoop: Grooper
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((== state 0) (self cue:))
			((super handleEvent: event))
			((Said 'get/note') (Print 14 55))
			(
			(or (Said 'stand,get[<up,dress]') (Said 'get<out/bed')) (ego setCycle: End self))
		)
	)
)

(instance fireWorkScript of Script
	(properties)
	
	(method (dispose)
		(if register (register dispose:))
		(if local1 (local1 dispose:))
		(if local2 (local2 dispose:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register
					((Prop new:)
						init:
						view: 114
						loop: 1
						cel: 0
						x: (Random 20 300)
						y: (Random 20 180)
						setCycle: End
						yourself:
					)
				)
				(= cycles 3)
			)
			(1
				(if local1 (local1 dispose:))
				(= local1
					((Prop new:)
						init:
						view: 114
						loop: 1
						cel: 0
						x: (Random 20 300)
						y: (Random 20 180)
						setCycle: End
						yourself:
					)
				)
				(= cycles 3)
			)
			(2
				(if local2 (local2 dispose:))
				(= local2
					((Prop new:)
						init:
						view: 114
						loop: 1
						cel: 0
						x: (Random 20 300)
						y: (Random 20 180)
						setCycle: End
						yourself:
					)
				)
				(= cycles 3)
			)
			(3
				(if register (register dispose:))
				(self init:)
			)
		)
	)
)

(instance messedUpBed of PV
	(properties
		y 105
		x 96
		view 314
		loop 3
		priority 6
	)
)

(instance sandals of View
	(properties
		y 81
		x 157
		view 314
		loop 3
		cel 1
	)
)

(instance bed2 of View
	(properties
		y 105
		x 126
		view 314
		loop 4
	)
)

(instance door of Prop
	(properties
		y 140
		x 49
		view 14
		loop 7
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (ego onControl: 1) 8192)
				(not (self script?))
			)
			(HandsOff)
			(self setScript: doorScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door[<building,room]]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 56))
					((Said 'close') (Print 14 57))
					((GoToIfSaid self event 42 146 'open' 14 18))
					((Said 'open') (Print 14 58))
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance doorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: End self)
				(theSound number: (SoundFX 36) loop: 1 play:)
			)
			(1
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 45) (ego y?) self
				)
			)
			(2
				(HandsOn)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance smoochSong of Sound
	(properties
		number 14
		loop -1
	)
)

(instance shirt of View
	(properties
		y 70
		x 187
		view 2
		loop 1
		cel 3
	)
)

(instance note of View
	(properties
		y 65
		x 173
		view 114
	)
	
	(method (init)
		(self setPri: 4)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/note,letter,message>')
				(cond 
					((Said 'look[<at]') (Print 14 59))
					((TurnIfSaid self event 'look[<at]/*'))
					((GoToIfSaid self event self 30 'get,read' 14 18))
					((Said 'get,read')
						(if z
							(AlreadyTook)
						else
							(Print 14 60)
							(Print 14 61)
							(Print 14 62)
							(theGame changeScore: 1)
							(self dispose:)
						)
					)
				)
			)
		)
	)
)

(instance closetFeat of Feature
	(properties
		y 150
		x 270
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/closet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 14 63))
					((Said 'open,examine') (Print 14 64))
				)
			)
		)
	)
)

(instance windowFeature of RFeature
	(properties
		y 60
		x 80
		nsTop 30
		nsLeft 65
		nsBottom 80
		nsRight 90
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,out]') (Print 14 0))
				)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		priority 2
	)
)
