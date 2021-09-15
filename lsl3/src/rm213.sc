;;; Sierra Script 1.0 - (do not remove this comment)
(script# 213)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm213 0
)

(local
	local0
	readingNewspaper
)
(instance rm213 of Room
	(properties
		picture 213
		horizon 5
		east 210
	)
	
	(method (init)
		(super init:)
		(if (Btst fFlag15)
			(= newspaperState NSshowroom)
		)
		(if newspaperState
			(aNewspaper init:)
		)
		(aRiver init:)
		(aRiver2 init:)
		(aTv init:)
		(self setScript: RoomScript)
		(if (not (Btst fCredits213))
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(ego posn: 314 163)
		(NormalEgo)
		(ego init:)
		(soundFX number: 213 loop: -1 play:)
	)
	
	(method (newRoom n)
		(music fade:)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(= cycles (= seconds 0))
				(HandsOff)
				(cond 
					((> (ego y?) 171)
						(ego setMotion: MoveTo (ego x?) 171 self)
					)
					((< (ego y?) 126)
						(ego setMotion: MoveTo (ego x?) 126 self)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(cond 
					((> (ego x?) 171)
						(ego setMotion: MoveTo 171 (ego y?) self)
					)
					((< (ego x?) 122)
						(ego setMotion: MoveTo 122 (ego y?) self)
					)
					(else
						(self cue:)
					)
				)
			)
			(3
				(ego setMotion: MoveTo (ego x?) 171 self)
			)
			(4
				(= cycles (= seconds 0))
				(ego view: 214 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(User canInput: TRUE)
				(= currentStatus egoSITTING)
				(if (== readingNewspaper TRUE)
					(= seconds 3)
				)
			)
			(6
				(ego setCel: 0 loop: 1)
				(= seconds (Random 1 4))
			)
			(7
				(ego setCycle: EndLoop self)
			)
			(8
				(ego setCycle: BegLoop self)
				(= state 5)
			)
			(9
				(HandsOff)
				(= cycles (= seconds 0))
				(if readingNewspaper
					(Print 213 30)
					(= readingNewspaper FALSE)
					(aNewspaper posn: 198 190)
				)
				(ego view: 214 setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(NormalEgo loopN)
				(= currentStatus egoNORMAL)
			)
			(11
				(= seconds 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'drag<on/camera,backdrop')
				(cond 
					((not (ego inRect: 134 125 164 131))
						(NotClose)
					)
					((< (aTv y?) 200)
						(ItIs)
					)
					(else
						(Ok)
						(aTv posn: 148 116)
					)
				)
			)
			((Said 'drag<off/camera,backdrop')
				(cond 
					((not (ego inRect: 134 125 164 131))
						(NotClose)
					)
					((> (aTv y?) 200)
						(ItIs)
					)
					(else
						(Ok)
						(aTv posn: 148 1116)
					)
				)
			)
			((Said 'lie')
				(cond 
					(playingAsPatti
						(Print 213 0)
					)
					((== currentStatus egoSITTING)
						(YouAre)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Ok)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== currentStatus egoNORMAL)
						(YouAre)
					)
					((!= currentStatus egoSITTING)
						(GoodIdea)
					)
					(else
						(self changeState: 9)
					)
				)
			)
			((Said 'swim')
				(Print 213 1)
			)
			((Said 'drag,alter/channel')
				(if (< (aTv y?) 200)
					(Print 213 2)
				else
					(Print 213 3)
				)
			)
			((Said 'drag<on/burn')
				(Print 213 4)
			)
			((Said 'drag<off/burn')
				(Print 213 5)
			)
			((Said 'caress,look<below,back,behind/buffet')
				(Print 213 6)
				(Print 213 7)
				(Print 213 8)
			)
			((Said 'look,caress<below/barstool')
				(Print 213 9)
			)
			(
				(or
					(Said '//camera,backdrop>')
					(Said '/camera,backdrop>')
				)
				(cond 
					((Said 'watch')
						(if (< (aTv y?) 200)
							(Print 213 10)
						else
							(Print 213 11)
						)
					)
					((Said 'look')
						(if (< (aTv y?) 200)
							(Print 213 10)
						else
							(Print 213 12)
						)
					)
					((Said 'adjust')
						(Print 213 13)
					)
					((Said 'get')
						(Print 213 14)
					)
					((Said 'caress,look<back,below,behind')
						(if (< (aTv y?) 200)
							(Print 213 15)
							(Print 213 16 #at -1 144)
							(theGame changeScore: -1)
						else
							(Print 213 17)
						)
					)
					(else
						(Print 213 18)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'get/buffet')
				(Printf 213 19 currentEgo)
			)
			((Said 'get/burn')
				(Print 213 20)
			)
			((Said 'look/barstool')
				(if (== currentStatus egoSITTING)
					(Print 213 21)
				else
					(Print 213 22)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/burn')
						(Printf 213 23 currentEgo)
					)
					((Said '/buffet')
						(cond 
							(readingNewspaper
								(Print 213 24)
							)
							((cast contains: aNewspaper)
								(Print 213 25)
							)
							(else
								(Print 213 26)
							)
						)
					)
					((Said '/creek,cascade,water')
						(Print 213 27)
					)
					((Said '/carpet')
						(Print 213 28)
					)
					((Said '[/area]')
						(Print 213 29)
					)
				)
			)
		)
	)
)

(instance aTv of Prop
	(properties
		y 1116
		x 148
		view 213
		loop 1
		priority 4
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)

(instance aRiver of Prop
	(properties
		y 104
		x 31
		view 213
		loop 2
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self isExtra: TRUE setCycle: Forward)
	)
)

(instance aRiver2 of Prop
	(properties
		y 141
		view 213
		loop 3
		cel 1
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self isExtra: TRUE setCycle: Forward)
	)
)

(instance aNewspaper of View
	(properties
		y 190
		x 198
		view 213
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setPri: 15
			z: (- (aNewspaper y?) 169)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				(event claimed?)
				(not (Said '/document>'))
			)
			(return)
		)
		(if (Said 'get')
			(cond 
				(playingAsPatti
					(Print 213 31)
				)
				(readingNewspaper
					(AlreadyTook)
				)
				((not (ego inRect: 170 161 221 187))
					(NotClose)
				)
				((and (!= currentStatus egoNORMAL) (!= currentStatus egoSITTING))
					(GoodIdea)
				)
				(else
					(Ok)
					(aNewspaper posn: 198 1190)
					(= readingNewspaper TRUE)
					(if (== currentStatus egoSITTING)
						(RoomScript changeState: 5)
					else
						(RoomScript changeState: 1)
					)
				)
			)
		)
		(if (Said 'backdrop,backdrop,close')
			(if (not readingNewspaper)
				(DontHave)
			else
				(Ok)
				(= readingNewspaper FALSE)
				(aNewspaper posn: 198 190)
				(ego view: 214 setLoop: 0 setCel: 255)
				(RoomScript cycles: 0 seconds: 0 changeState: 5)
			)
		)
		(if (Said 'look')
			(if (not readingNewspaper)
				(DontHave)
			else
				(switch newspaperState
					(NSshowroom
						(Print 213 32)
						(Print 213 33 #font bigFont #mode teJustCenter #at -1 30 #width 234)
					)
					(NSpComing
						(Print 213 34)
						(Print 213 35 #font bigFont #mode teJustCenter #at -1 30 #width 234)
					)
					(NSpHere
						(Print 213 36 #font bigFont #mode teJustCenter #at -1 30 #width 234)
					)
					(else 
						(Print 213 37)
						(Print 213 38 #at -1 144)
					)
				)
			)
		)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 215
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 288
		view 215
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 22)
			)
			(3
				(Bset fCredits213)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)
