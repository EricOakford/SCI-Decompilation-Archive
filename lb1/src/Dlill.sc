;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include game.sh)
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
	foundInsignia
	pickUpState
	lillHadGun
	lillWasShot
)
(instance myMusic of Sound
	(properties
		number 120
		priority 5
	)
)

(enum
	getGUN
	getBULLET
	getKEY
	getHEEL
)

(instance Body of Prop
	(properties
		y 114
		x 91
		view 503
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/lil'))
			(Print 280 0)
			(Print 280 1)
			(event claimed: TRUE)
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
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
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
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 280 3)
		)
	)
)

(instance Dlill of Region

	(method (init)
		(Body ignoreActors: TRUE init: stopUpd:)
		(if (not gunIsLoaded)
			(if (not (ego has: iBullet))
				(= lillWasShot TRUE)
				(Shot init: stopUpd:)
			)
			(if (not (ego has: iDerringer))
				(= lillHadGun TRUE)
				(Pistol init: stopUpd:)
			)
		)
		(= global195 32)
		(if (not (& deadGuests $0040))
			(= [global368 0] 3600)
			(= global111 75)
			(self setScript: showCloseup)
		)
	)
	
	(method (doit)
		(ego observeControl: cGREY)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said '/lil>')
						(cond 
							((Said 'kill')
								(Print 280 4)
							)
							((Said 'kiss')
								(Print 280 5)
							)
							((Said 'embrace')
								(Print 280 6)
							)
							((Said 'get,drag,drag,press,move')
								(Print 280 7)
							)
							((Said '(examine<in),search')
								(if (< (ego distanceTo: Body) 25)
									(if (not (ego has: iSkeletonKey))
										(= pickUpState 3)
										(self setScript: pickUp)
									else
										(Print 280 8)
									)
								else
									(NotClose)
								)
							)
							((Said 'help')
								(Print 280 9)
							)
							((Said 'converse')
								(Print 280 10)
							)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/derringer')
								(Bset 26)
								(cond 
									((ego has: iDerringer)
										(AlreadyTook)
									)
									((< (ego distanceTo: Pistol) 10)
										(= pickUpState 1)
										(= lillHadGun FALSE)
										(Pistol setScript: pickUp)
									)
									(else
										(NotClose)
									)
								)
							)
							((Said '/bullet')
								(cond 
									(gunIsLoaded
										(Print 280 11)
									)
									((ego has: iBullet)
										(AlreadyTook)
									)
									((< (ego distanceTo: Shot) 10)
										(= lillWasShot 0)
										(= pickUpState 2)
										(Shot setScript: pickUp)
									)
									(else
										(NotClose)
									)
								)
							)
							((Said '/cape')
								(Print 280 12)
							)
							((Said '/hat')
								(Print 280 13)
							)
							((Said '/boot')
								(Print 280 14)
							)
							((Said '/glove')
								(Print 280 15)
							)
						)
					)
					((Said 'examine>')
						(cond 
							((Said '<in/cape')
								(Print 280 16)
								(if (not (ego has: iSkeletonKey))
									(self setScript: pickUp)
								else
									(Print 280 17)
								)
							)
							((Said '/cape')
								(Print 280 18)
							)
							((Said '<in/hat')
								(Print 280 19)
							)
							((Said '/hat')
								(Print 280 20)
							)
							((Said '<in/boot')
								(Print 280 21)
							)
							((Said '/boot')
								(Print 280 22)
							)
							((Said '<in/glove')
								(Print 280 23)
							)
							((Said '/glove')
								(Print 280 24)
							)
							((Said '/insignia,heel')
								(if (< (ego distanceTo: Body) 40)
									(= pickUpState 4)
									(self setScript: pickUp)
								else
									(NotClose)
								)
							)
							((or (Said '/dirt') (Said '<down'))
								(cond 
									(lillHadGun
										(Print 280 25)
									)
									(lillWasShot
										(Print 280 2)
									)
									(else
										(event claimed: FALSE)
									)
								)
							)
						)
					)
					((Said 'wear>')
						(cond 
							((Said '/cape')
								(Print 280 12)
							)
							((Said '/hat')
								(Print 280 13)
							)
							((Said '/boot')
								(Print 280 14)
							)
							((Said '/glove')
								(Print 280 15)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance showCloseup of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& deadGuests deadLILLIAN))
						(|= deadGuests deadLILLIAN)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(myMusic play:)
				(Print 280 26
					#at 10 75
					#icon 503 3 0
					#mode teJustCenter
				)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (== pickUpState 1) (== pickUpState 2))
					(Face ego client)
				else
					(Face ego Body)
				)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				(if (or (== pickUpState 1) (== pickUpState 2))
					(client hide:)
				)
				(switch pickUpState
					(getGUN
						(= gotItem TRUE)
						(ego get: iDerringer)
						(if (ego has: iBullet)
							(Ok)
						else
							(Print 280 27)
						)
					)
					(getKEY
						(= gotItem TRUE)
						(ego get: iSkeletonKey)
						(Print 280 16)
						(Print 280 28)
					)
					(getBULLET
						(= gotItem TRUE)
						(ego get: iBullet)
						(Ok)
					)
					(getHEEL
						(switch foundInsignia
							(0 (Print 280 29))
							(else  (Print 280 30))
						)
						(++ foundInsignia)
					)
				)
				(= cycles 2)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(if (or (== pickUpState 1) (== pickUpState 2))
					(client dispose:)
				)
				(client setScript: 0)
			)
		)
	)
)
