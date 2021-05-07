;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Dlill 0
)
(synonyms
	(lil body girl)
)

(local
	local0
	local1
	local2
	local3
)
(instance myMusic of Sound
	(properties
		number 120
		priority 5
	)
)

(instance Body of Prop
	(properties
		y 114
		x 91
		view 503
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/lil'))
			(Print 280 0)
			(Print 280 1)
			(event claimed: 1)
		)
	)
)

(instance Shot of Prop
	(properties
		y 105
		x 72
		view 503
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 280 2)
		)
	)
)

(instance Pistol of Prop
	(properties
		y 122
		x 61
		view 503
		loop 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 280 3)
		)
	)
)

(instance Dlill of Rgn
	(properties)
	
	(method (init)
		(Body ignoreActors: 1 init: stopUpd:)
		(if (not gunIsLoaded)
			(if (not (ego has: 14))
				(= local3 1)
				(Shot init: stopUpd:)
			)
			(if (not (ego has: 15))
				(= local2 1)
				(Pistol init: stopUpd:)
			)
		)
		(= global195 32)
		(if (not (& global123 $0040))
			(= [global368 0] 3600)
			(= global111 75)
			(self setScript: showCloseup)
		)
	)
	
	(method (doit)
		(ego observeControl: 256)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said '/lil>')
						(cond 
							((Said 'kill') (Print 280 4))
							((Said 'kiss') (Print 280 5))
							((Said 'embrace') (Print 280 6))
							((Said 'get,drag,drag,press,move') (Print 280 7))
							((Said '(examine<in),search')
								(if (< (ego distanceTo: Body) 25)
									(if (not (ego has: 5))
										(= local1 3)
										(self setScript: pickUp)
									else
										(Print 280 8)
									)
								else
									(NotClose)
								)
							)
							((Said 'help') (Print 280 9))
							((Said 'converse') (Print 280 10))
						)
					)
					((Said 'get>')
						(cond 
							((Said '/derringer')
								(Bset 26)
								(cond 
									((ego has: 15) (AlreadyTook))
									((< (ego distanceTo: Pistol) 10) (= local1 1) (= local2 0) (Pistol setScript: pickUp))
									(else (NotClose))
								)
							)
							((Said '/bullet')
								(cond 
									(gunIsLoaded (Print 280 11))
									((ego has: 14) (AlreadyTook))
									((< (ego distanceTo: Shot) 10) (= local3 0) (= local1 2) (Shot setScript: pickUp))
									(else (NotClose))
								)
							)
							((Said '/cape') (Print 280 12))
							((Said '/hat') (Print 280 13))
							((Said '/boot') (Print 280 14))
							((Said '/glove') (Print 280 15))
						)
					)
					((Said 'examine>')
						(cond 
							((Said '<in/cape')
								(Print 280 16)
								(if (not (ego has: 5))
									(self setScript: pickUp)
								else
									(Print 280 17)
								)
							)
							((Said '/cape') (Print 280 18))
							((Said '<in/hat') (Print 280 19))
							((Said '/hat') (Print 280 20))
							((Said '<in/boot') (Print 280 21))
							((Said '/boot') (Print 280 22))
							((Said '<in/glove') (Print 280 23))
							((Said '/glove') (Print 280 24))
							((Said '/insignia,heel')
								(if (< (ego distanceTo: Body) 40)
									(= local1 4)
									(self setScript: pickUp)
								else
									(NotClose)
								)
							)
							((or (Said '/dirt') (Said '<down'))
								(cond 
									(local2 (Print 280 25))
									(local3 (Print 280 2))
									(else (event claimed: 0))
								)
							)
						)
					)
					((Said 'wear>')
						(cond 
							((Said '/cape') (Print 280 12))
							((Said '/hat') (Print 280 13))
							((Said '/boot') (Print 280 14))
							((Said '/glove') (Print 280 15))
						)
					)
				)
			else
				0
			)
		)
	)
)

(instance showCloseup of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global123 $0040))
						(= global123 (| global123 $0040))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(myMusic play:)
				(Print 280 26 #at 10 75 #icon 503 3 0 #mode 1)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (== local1 1) (== local1 2))
					(Face ego client)
				else
					(Face ego Body)
				)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: End self)
			)
			(2
				(if (or (== local1 1) (== local1 2)) (client hide:))
				(switch local1
					(1
						(= gotItem 1)
						(ego get: 15)
						(if (ego has: 14) (Ok) else (Print 280 27))
					)
					(3
						(= gotItem 1)
						(ego get: 5)
						(Print 280 16)
						(Print 280 28)
					)
					(2
						(= gotItem 1)
						(ego get: 14)
						(Ok)
					)
					(4
						(switch local0
							(0 (Print 280 29))
							(else  (Print 280 30))
						)
						(++ local0)
					)
				)
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(if (or (== local1 1) (== local1 2))
					(client dispose:)
				)
				(client setScript: 0)
			)
		)
	)
)
