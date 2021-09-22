;;; Sierra Script 1.0 - (do not remove this comment)
(script# 375)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm375 0
)

(instance rm375 of Room
	(properties
		picture 375
		horizon 1
		south 370
	)
	
	(method (init)
		(if (ego has: iSoap)
			(Load VIEW 5)
		)
		(aWater1 init:)
		(aWater2 init:)
		(aWater3 init:)
		(aWater4 init:)
		(aDrain init:)
		(if (Btst fShowerOn)
			(self picture: 376)
			(Load PICTURE 375)
			(Load VIEW 376)
			(soundFX number: 375 loop: musicLoop play:)
		else
			(self picture: 375)
			(Load PICTURE 376)
			(Load VIEW 377)
			(Load SOUND 375)
			(aWater1 hide:)
			(aWater2 hide:)
			(aWater3 hide:)
			(aWater4 hide:)
			(aDrain hide:)
		)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(ego
			view: (cond 
				((Btst fShowerOn) 377)
				((>= filthLevel 3) 376)
				(else 378)
			)
			posn: 53 176
			setPri: 11
			setStep: 5 5
			init:
		)
	)
	
	(method (newRoom n)
		(soundFX fade:)
		(if (InRoom iTowel 375)
			(ego get: iTowel)
			(Print 375 0)
		)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (and (& (ego onControl:) cGREEN) (Btst fShowerOn))
			(Bset fWet)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'shampoo')
				(Print 375 1)
			)
			((or (Said 'caress/self') (Said 'jack'))
				(Ok)
				(Print 375 2
					#at -1 144
				)
			)
			((Said 'cease,(drag<off)/bath,faucet,channel,handle,water')
				(cond 
					((not (Btst fShowerOn))
						(ItIs)
					)
					((not (& (ego onControl:) cBLUE))
						(Print 375 3)
					)
					(else
						(Ok)
						(ego view: (if (>= filthLevel 3) 376 else 378))
						(Bclr fShowerOn)
						(curRoom drawPic: 375 8)
						(aWater1 hide:)
						(aWater2 hide:)
						(aWater3 hide:)
						(aWater4 hide:)
						(aDrain hide:)
						(soundFX stop:)
					)
				)
			)
			(
				(or
					(Said 'bath,bath')
					(Said 'get/bath')
					(Said 'use,(drag<on)/bath,faucet,channel,handle,water')
				)
				(cond 
					((Btst fShowerOn)
						(ItIs)
					)
					((not (& (ego onControl:) cBLUE))
						(Print 375 3)
					)
					(else
						(Ok)
						(ego view: 377)
						(Bset fShowerOn)
						(curRoom drawPic: 376 8)
						(if (> machineSpeed 16)
							(aWater1 show:)
							(aWater2 show:)
							(aWater3 show:)
							(aWater4 show:)
						)
						(aDrain show:)
						(soundFX number: 375 loop: musicLoop play:)
					)
				)
			)
			((Said 'rinse')
				(cond 
					((not (Btst fShowerOn))
						(Print 375 4)
					)
					((not (& (ego onControl:) cGREEN))
						(Print 375 5)
					)
					(else
						(Ok)
						(Print 375 6)
					)
				)
			)
			((or (Said 'clean') (Said 'clean,use/soap'))
				(cond 
					((not (Btst fShowerOn))
						(Print 375 4)
					)
					((not (& (ego onControl:) cGREEN))
						(Print 375 5)
					)
					((not (ego has: iSoap))
						(Print 375 7)
						(Print 375 8 #at -1 144)
					)
					(else
						(Ok)
						(Bclr fNotShower)
						(Bclr fNotUseSoap)
						(theGame changeScore: 60)
						(ego put: iSoap -1)
						(Print 375 9 #icon 5 0 0)
						(Print 375 10)
						(if (>= filthLevel 3)
							(Print 375 11)
						)
						(Print 375 12)
					)
				)
			)
			((and (not (ego has: iSoap)) (Said 'get/soap'))
				(Print 375 13)
				(Print 375 14
					#at -1 144
				)
			)
			((Said 'look>')
				(cond 
					((Said '/faucet,handle,channel')
						(if (Btst fShowerOn)
							(Print 375 15)
						else
							(Print 375 16)
						)
					)
					((Said '/drain')
						(if (Btst fShowerOn)
							(Printf 375 17 (if (Random 0 1) {counter-} else {}))
						else
							(Print 375 18)
						)
					)
					((Said '[/carpet,bath,area]')
						(Print 375 19)
					)
				)
			)
		)
	)
)

(instance aWater1 of Prop
	(properties
		y 91
		x 148
		view 375
		loop 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aWater2 of Prop
	(properties
		y 61
		x 178
		view 375
		loop 2
		cel 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aWater3 of Prop
	(properties
		y 92
		x 170
		view 375
		loop 1
		cel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aWater4 of Prop
	(properties
		y 84
		x 128
		view 375
		loop 2
		cel 4
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aDrain of Prop
	(properties
		y 91
		x 148
		view 375
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward ignoreActors:)
	)
)
