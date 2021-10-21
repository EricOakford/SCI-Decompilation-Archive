;;; Sierra Script 1.0 - (do not remove this comment)
(script# regJet)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)

(public
	jet 0
	AirplanePrint 1
	GoToBathroom 2
)
(synonyms
	(attendant attendant)
)

(procedure (AirplanePrint)
	(Print &rest
		#at 10 10
		#font smallFont
	)
)

(procedure (GoToBathroom)
	(addToPics
		add:
			toilet
			seat1
			seat2
			seat3
			seat4
			seat5
			seat6
			seat7
			seat8
			seat9
			seat10
			seat11
			seat12
			seat13
			seat14
			seat15
			seat16
			window1
			window2
			window3
			window4
			window5
			window6
			window7
			bath1
			bath2
			trash
			sink
			mirror
			LCutWall
	)
	(addToPics doit:)
)

(instance jet of Locale
	(method (handleEvent event &tmp evt)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					((== (= evt (event message?)) `#6)
						(event claimed: TRUE)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(
								(or
									(not (ego has: iAmmoClips))
									(and
										(== [numAmmoClips 1] [numAmmoClips 2])
										(== [numAmmoClips 2] 0)
									)
								)
								(Print 154 0)
							)
							([numAmmoClips bulletsInGun]
								(Print 154 1)
							)
							((and isHandsOff (not sittingInPlane)) 0)
							(else
								(Print 154 2 #time 4)
								(if (== bulletsInGun 1)
									(= bulletsInGun 2)
								else
									(= bulletsInGun 1)
								)
							)
						)
					)
					((== evt `#8)
						(event claimed: TRUE)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							((== curRoomNum 41)
								(event claimed: FALSE)
							)
							(else
								(Print 154 3)
							)
						)
					)
				)
			)
			(saidEvent
				(cond 
					((Said '/compartment')
						(AirplanePrint 154 4)
					)
					((Said '/door>')
						(cond 
							((Said 'open,beat')
								(cond 
									((& (ego onControl:) cLMAGENTA)
										(Print 154 5)
									)
									((& (ego onControl:) cLRED)
										(Print 154 6)
									)
									(else
										(NotClose)
									)
								)
							)
							((Said 'knock,beat')
								(cond 
									((& (ego onControl:) cLMAGENTA)
										(Print 154 7)
									)
									((& (ego onControl:) cLRED)
										(Print 154 8)
									)
									(else
										(NotClose)
									)
								)
							)
							((Said 'unlock')
								(Print 154 9)
							)
							(else
								(event claimed: TRUE)
								(Print 154 10)
							)
						)
					)
					((Said 'chat/passenger,dude,broad')
						(if (not sittingInPlane)
							(switch (Random 81 85)
								(81 (AirplanePrint 154 11))
								(82 (AirplanePrint 154 12))
								(83 (AirplanePrint 154 13))
								(84 (AirplanePrint 154 14))
								(85 (AirplanePrint 154 15))
							)
						else
							(Print 154 16)
						)
					)
					((Said 'pinch[/attendant]')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17)
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 18)
							)
						)
					)
					((Said 'chat,call/attendant')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17)
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 19)
							)
						)
					)
					((Said 'use,go/crapper,bathroom,(chamber<(bath,rest))')
						(Print 154 20)
					)
					((Said 'display/badge')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17)
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 21)
							)
						)
					)
					((Said '[kiss,fuck][/naked,boob,sex]')
						(cond 
							((not (cast contains: stewardess))
								(Print 154 17)
							)
							((> (ego distanceTo: stewardess) 25)
								(NotClose)
							)
							(else
								(AirplanePrint 154 22)
								(AirplanePrint 154 23)
							)
						)
					)
					((Said 'fasten,deposit,wear,buckle/belt,belt')
						(cond 
							((not sittingInPlane)
								(Print 154 24)
							)
							(wearingSeatbelt
								(Print 154 25)
							)
							(else
								(Print 154 26)
								(= wearingSeatbelt TRUE)
							)
						)
					)
					(
					(Said 'unfasten,unbuckle,remove,(get<off)/belt,belt')
						(cond 
							((not sittingInPlane)
								(Print 154 24)
							)
							((not wearingSeatbelt)
								(Print 154 27)
							)
							(wearingSeatbelt
								(Print 154 26)
								(= wearingSeatbelt FALSE)
							)
						)
					)
					((Said 'sat')
						(if sittingInPlane
							(Print 154 28)
						else
							(Print 154 29)
						)
					)
					((Said 'stand,(get<up)')
						(cond 
							(wearingSeatbelt
								(Print 154 30)
							)
							((not sittingInPlane)
								(Print 154 31)
							)
							(else
								(Print 154 32)
							)
						)
					)
					((Said 'buy,order')
						(Print 154 33)
					)
					((Said '/captain')
						(AirplanePrint 154 34))
					((Said 'meditate,nap')
						(Print 154 35)
					)
					((Said 'look>')
						(cond 
							((Said '/attendant')
								(if (not (cast contains: stewardess))
									(Print 154 17)
								else
									(AirplanePrint 154 36)
								)
							)
							((Said '/passenger')
								(AirplanePrint 154 37)
							)
							((Said '/dude,broad')
								(AirplanePrint 154 38)
							)
							((Said '/pane')
								(AirplanePrint 154 39)
							)
							((Said '/bench')
								(AirplanePrint 154 40)
							)
							((Said '/bathroom')
								(AirplanePrint 154 20)
							)
							((Said '[<at,around][/(noword,chamber,airplane)]')
								(if (ego inRect: 63 152 75 161)
									(AirplanePrint 154 41)
								else
									(AirplanePrint 154 42)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
		)
	)
)

(instance seat1 of PicView
	(properties
		y 72
		x 205
		view 82
		loop 1
		cel 3
		priority 1
		signal ignrAct
	)
)

(instance seat2 of PicView
	(properties
		y 82
		x 186
		view 82
		loop 1
		cel 5
		priority 1
		signal ignrAct
	)
)

(instance seat3 of PicView
	(properties
		y 92
		x 165
		view 82
		loop 1
		priority 1
		signal ignrAct
	)
)

(instance seat4 of PicView
	(properties
		y 102
		x 146
		view 82
		loop 1
		cel 6
		priority 1
		signal ignrAct
	)
)

(instance seat5 of PicView
	(properties
		y 112
		x 125
		view 82
		loop 1
		cel 1
		priority 1
		signal ignrAct
	)
)

(instance seat6 of PicView
	(properties
		y 123
		x 103
		view 82
		loop 1
		priority 1
		signal ignrAct
	)
)

(instance seat7 of PicView
	(properties
		y 135
		x 80
		view 82
		loop 1
		cel 2
		priority 1
		signal ignrAct
	)
)

(instance seat8 of PicView
	(properties
		y 146
		x 58
		view 82
		loop 1
		cel 4
		priority 1
		signal ignrAct
	)
)

(instance seat9 of PicView
	(properties
		y 90
		x 253
		view 82
		loop 1
		cel 5
		priority 14
		signal ignrAct
	)
)

(instance seat10 of PicView
	(properties
		y 100
		x 234
		view 82
		loop 1
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance seat11 of PicView
	(properties
		y 110
		x 213
		view 82
		loop 1
		cel 6
		priority 14
		signal ignrAct
	)
)

(instance seat12 of PicView
	(properties
		y 120
		x 193
		view 82
		loop 1
		cel 4
		priority 14
		signal ignrAct
	)
)

(instance seat13 of PicView
	(properties
		y 131
		x 171
		view 82
		loop 1
		priority 14
		signal ignrAct
	)
)

(instance seat14 of PicView
	(properties
		y 141
		x 151
		view 82
		loop 1
		cel 2
		priority 14
		signal ignrAct
	)
)

(instance seat15 of PicView
	(properties
		y 151
		x 131
		view 82
		loop 1
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance seat16 of PicView
	(properties
		y 162
		x 109
		view 82
		loop 1
		cel 5
		priority 14
		signal ignrAct
	)
)

(instance window1 of PicView
	(properties
		y 49
		x 203
		view 82
		loop 6
		priority 0
	)
)

(instance window2 of PicView
	(properties
		y 59
		x 183
		view 82
		loop 6
		priority 0
	)
)

(instance window3 of PicView
	(properties
		y 69
		x 163
		view 82
		loop 6
		priority 0
	)
)

(instance window4 of PicView
	(properties
		y 80
		x 141
		view 82
		loop 6
		priority 0
	)
)

(instance window5 of PicView
	(properties
		y 91
		x 119
		view 82
		loop 6
		priority 0
	)
)

(instance window6 of PicView
	(properties
		y 103
		x 95
		view 82
		loop 6
		priority 0
	)
)

(instance window7 of PicView
	(properties
		y 116
		x 69
		view 82
		loop 6
		priority 0
	)
)

(instance bath1 of PicView
	(properties
		y 41
		x 246
		view 82
		signal ignrAct
	)
)

(instance bath2 of PicView
	(properties
		y 167
		x 65
		view 82
		loop 2
		cel 1
		priority 12
		signal ignrAct
	)
)

(instance toilet of PicView
	(properties
		y 178
		x 68
		view 82
		cel 4
		priority 14
		signal ignrAct
	)
)

(instance trash of PicView
	(properties
		y 181
		x 32
		view 82
		cel 3
		priority 14
		signal ignrAct
	)
)

(instance sink of PicView
	(properties
		y 159
		x 27
		view 82
		cel 2
		signal ignrAct
	)
)

(instance mirror of PicView
	(properties
		y 143
		x 21
		view 82
		cel 1
		signal ignrAct
	)
)

(instance LCutWall of PicView
	(properties
		y 54
		x 242
		view 82
		cel 5
		priority 2
		signal ignrAct
	)
)
