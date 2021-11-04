;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Wander)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm15 0
)

(local
	local0
	newAutoDoor
	newAct
	rosePrice
	bouquetPrice
	plantPrice
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	local19
)
(procedure (LocPrint)
	(if (> (ego y?) 150)
		(Print &rest #at -1 15)
	else
		(Print &rest #at -1 120)
	)
)

(instance flowerSounds of Sound
	(properties
		number 20
	)
)

(instance egoSquashed of Sound
	(properties
		number 23
	)
)

(instance taxi of View
	(properties)
)

(instance flowerGirl of Actor
	(properties)
)

(instance lightPole of Prop
	(properties)
)

(instance rm15 of Room
	(properties
		picture 15
		style HWIPE
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 76)
		(if
			(or
				(ego has: iFlower)
				(< gamePhase 8)
			)
			(Load VIEW 43)
		)
		(super init:)
		(= perspective 70)
		(= local19 0)
		(HandsOn)
		(= gunFireState 3)
		((View new:)
			view: 76
			loop: 3
			cel: 0
			posn: 120 52
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 0
			cel: 0
			posn: 263 122
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 0
			cel: 1
			posn: 297 123
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 0
			cel: 2
			posn: 273 124
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 0
			cel: 3
			posn: 251 124
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 0
			cel: 4
			posn: (if (== (Random 1 2) 1) 250 else 272) 124
			init:
			addToPic:
		)
		((View new:)
			view: 76
			loop: 4
			cel: 0
			posn: 38 140
			init:
			addToPic:
		)
		(lightPole
			view: 76
			loop: 0
			cel: 6
			posn: 73 128
			init:
			stopUpd:
			setScript: lightScript
		)
		((= newAutoDoor (AutoDoor new:))
			view: 76
			loop: 1
			posn: 117 108
			entranceTo: 16
			facingLoop: 3
			cycleSpeed: 1
			init:
			stopUpd:
		)
		(= rosePrice 2)
		(= bouquetPrice 15)  bouquetPrice
		(= plantPrice 7)
		(self setLocales: 153)
		(self setScript: rm15Script)
	)
	
	(method (doit)
		(newAutoDoor doit:)
		(if (cast contains: keith)
			(keith setMotion: Follow ego 25)
		)
		(cond 
			(local19 0)
			((> (ego x?) 325)
				(LocPrint 15 0)
				(ego setMotion: MoveTo 300 (ego y?))
			)
			((< (ego x?) -5)
				(LocPrint 15 0)
				(ego setMotion: MoveTo 20 (ego y?))
			)
		)
		(cond 
			((<= (ego y?) 124)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(if
			(and
				(not local8)
				(not local12)
				(not (ego has: iFlower))
				(< gamePhase 8)
				(or
					(== prevRoomNum 16)
					(and
						(== prevRoomNum 14)
						(< (ego y?) 140)
					)
				)
			)
			(flowerScript changeState: 0)
		)
		(if
			(and
				local10
				(or
					(== (flowerScript state?) 0)
					(== (flowerScript state?) 1)
				)
			)
			(cond 
				((> (ego x?) 245)
					(= local18 1)
					(flowerGirl setMotion: 0)
				)
				(local18 (= local18 0)
					(flowerGirl setMotion: Follow ego 25)
				)
			)
		)
		(if
			(and
				(not local10)
				(not local11)
				local8
				(<= (flowerGirl distanceTo: ego) 25)
			)
			(= local10 1)
			(= local9 0)
			(flowerScript changeState: 1)
		)
		(if
			(and
				local8
				(> (ego y?) 145)
			)
			(flowerGirl stopUpd:)
		else
			(flowerGirl startUpd:)
		)
		(cond 
			((not local16)
				(if
					(and
						(not local6)
						(> 176 (ego y?)
					)
					(> (ego y?) 142))
					(= local16 1)
					(if (<= (ego y?) 150)
						(= local17 2)
					else
						(= local17 -2)
					)
					(egoSquashed play:)
					(taxi posn: 450 (+ (ego y?) 2))
				)
			)
			((< (taxi x?) -35)
				(if (< (rm15Script state?) 1)
					(= local16 0)
				)
			)
			(else
				(taxi
					posn:
						(- (taxi x?) 50)
						(if 
							(and
								(> 173 (ego y?))
								(> (ego y?) 142)
							)
							(+ (ego y?) 2)
						else
							(taxi y?)
						)
					setPri: (+ (ego priority?) local17)
				)
				(if (> (taxi priority?) 14)
					(taxi priority: 15)
				)
				(if (< (taxi priority?) 10)
					(taxi priority: 10)
				)
			)
		)
		(cond 
			((< (ego x?) -20)
				(ego x: -20)
			)
			(
				(and
					(< -25 (- (taxi x?) (ego x?)))
					(< (- (taxi x?) (ego x?)) 65)
					(> 9 (- (taxi y?) (ego y?)))
					(> (- (taxi y?) (ego y?)) -6)
					(< (rm15Script state?) 1)
					(> 176 (ego y?))
					(> (ego y?) 138)
					(not local6)
				)
				(rm15Script changeState: 1)
			)
			((== (newAutoDoor doorState?) 2)
				(= perspective 0)
				(curRoom newRoom: 16)
			)
			((> (ego y?) 210)
				(= gGEgoX (ego x?))
				(= gunFireState 2)
				(= perspective 0)
				(curRoom newRoom: 14)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(flowerScript dispose:)
		(DisposeScript 301)
		(super dispose:)
	)
)

(instance rm15Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(StatusLine enable:)
				(if (== prevRoomNum 14)
					(cond 
						((< gGEgoX 58)
							(ego
								view: (if gunDrawn 7 else 1)
								posn: -10 189
								setCycle: Walk
								init:
								setMotion: MoveTo 25 189
							)
						)
						((> gGEgoX 161)
							(ego
								view: (if gunDrawn 7 else 1)
								posn: 327 189
								setCycle: Walk
								init:
								setMotion: MoveTo 275 189
							)
						)
						(else
							(ego
								view: (if gunDrawn 7 else 1)
								posn: (* (- gGEgoX 57) 3) 195
								setCycle: Walk
								init:
								setMotion: MoveTo (* (- gGEgoX 57) 3) 189
							)
						)
					)
				else
					(ego
						view: (if gunDrawn 6 else 0)
						posn: 115 117
						setCycle: Walk
						init:
						illegalBits: -32768
						setMotion: MoveTo 115 122
					)
				)
				(if (Btst fKeithFollows)
					((= keith (Actor new:))
						view: 20
						init:
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Follow ego 25
					)
					(if (== prevRoomNum 16)
						(keith posn: 103 113 loop: 2)
					else
						(keith posn: (+ (ego x?) 20) 200)
					)
				)
				(taxi
					view: 76
					loop: 2
					cel: 0
					posn: 450 (if (< (ego y?) 176) (ego y?) else 176)
					init:
					ignoreActors:
				)
			)
			(1
				(HandsOff)
				((= newAct (Actor new:))
					view: 76
					posn: (if (> (ego x?) 325) 322 else (ego x?)) (ego y?)
					loop: 5
					cel: 0
					setMotion: 0
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
				(ego dispose:)
				(= local15 0)
				(= local19 1)
			)
			(2
				(newAct
					setLoop: 6
					cel: 0
					setMotion: MoveTo (- (newAct x?) 20) (newAct y?)
					setCycle: EndLoop self
				)
			)
			(3
				(newAct
					setLoop: 7
					setCel: 0
					stopUpd:
					addToPic:
				)
				(= cycles 12)
				(User canInput: 0)
			)
			(4
				(if (Btst fKeithFollows)
					(keith loop: 1)
					(LocPrint 15 1 83)
				)
				(= cycles 12)
			)
			(5
				(Print 15 2 #width 26 #at 221 62 #time 3)
				(Print 15 3 #width 50 #at 252 60 #time 4)
				(if local7
					(EgoDead
						{You took too much time getting across the street. Too bad...so sad...you've been had!}
					)
				else
					(EgoDead
						{You forgot to look both ways and wait for the light. Didn't your mother tell you about that?}
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said 'gave/newspaper')
							(Said 'ask,get/newspaper')
						)
						(if (ego inRect: 206 110 305 145)
							(LocPrint 15 4)
						else
							(LocPrint 15 5)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/ave,sidewalk,bridge,(walk<side)')
								(LocPrint 15 6)
							)
							((Said '/box')
								(LocPrint 15 7)
							)
							((Said '/pane')
								(LocPrint 15 8)
							)
							((Said '/men,dude,person,crowd')
								(Print 15 9 #at -1 15)
							)
							((Said '/bench')
								(LocPrint 15 10)
							)
							((Said '/advertise,ad')
								(LocPrint 15 11)
							)
							((Said '/way<both')
								(LocPrint 15 12)
							)
							((Said '/awning')
								(LocPrint 15 13)
							)
							((Said '<below/auto')
								(LocPrint 15 14)
							)
							((Said '/auto')
								(if (ego inRect: 80 108 120 150)
									(LocPrint 15 15)
								else
									(LocPrint 15 16)
								)
							)
							((Said '/door')
								(LocPrint 15 17)
							)
							((Said '/sign')
								(LocPrint 15 18 25 4)
								(LocPrint 15 19)
							)
							(
								(or
									(Said '/air')
									(Said '<up')
								)
								(LocPrint 15 20)
							)
							(
								(or
									(Said '/dirt')
									(Said '<down')
								)
								(LocPrint 15 21)
							)
							((Said '[<at,around][/building,airport,terminal]')
								(Print 15 22 #at -1 15)
							)
							((Said '/pole,light[<traffic]')
								(if (> (ego y?) 150)
									(LocPrint 15 23)
								else
									(LocPrint 15 24)
								)
							)
							((Said '/button')
								(if (== (ego onControl: 1) cLCYAN)
									(LocPrint 15 25)
								else
									(LocPrint 15 26)
								)
							)
							((Said '/flower,basket')
								(cond 
									(local10
										(Print 15 27 #at -1 15)
									)
									(local9
										(LocPrint 15 28)
									)
									(local11
										(LocPrint 15 29)
									)
									(else
										(event claimed: 0)
									)
								)
							)
							((Said '/broad,wanda')
								(cond 
									(
										(or
											local10
											local9
										)
										(LocPrint 15 30)
									)
									(local11
										(LocPrint 15 29)
									)
									(else
										(LocPrint 15 31)
									)
								)
							)
						)
					)
					((Said 'display/shot,mugshot,painting')
						(if
							(or
								(ego has: iNewMugShot)
								(ego has: iOldMugShot)
							)
							(if
								(or
									(ego inRect: 206 110 305 145)
									local10
								)
								(LocPrint 15 32)
							else
								(LocPrint 15 33)
							)
						)
					)
					(
						(or
							(Said 'display,see,ask,chat/flower,rose,bouquet,plant')
							(Said 'display,ask,chat[/broad,i]/flower,rose,bouquet,plant')
							(Said '(have<do)<what')
						)
						(cond 
							((not local8)
								(LocPrint 15 34)
							)
							(local9
								(LocPrint 15 28)
							)
							(local11
								(LocPrint 15 29)
							)
							(else
								(Print 15 27 #at -1 15)
							)
						)
					)
					(
					(Said 'gave/flower,rose,bouquet,carnation,plant')
						(cond 
							(local8
								(LocPrint 15 35)
							)
							(local11
								(LocPrint 15 29)
							)
							(else
								(LocPrint 15 36)
							)
						)
					)
					(
						(Said 'smell/flower,rose,carnation,bouquet,plant')
						(if (ego has: iFlower)
							(LocPrint 15 37)
						else
							(LocPrint 15 38)
						)
					)
					((Said 'polish')
						(LocPrint 15 39)
					)
					((Said 'display/badge')
						(cond 
							((ego inRect: 206 110 305 145)
								(LocPrint 15 40)
							)
							(local10
								(LocPrint 15 41)
							)
							(else
								(LocPrint 15 42)
							)
						)
					)
					(
						(or
							(Said 'stay')
							(Said 'come<back')
							(Said 'dont')
							(Said 'cease')
						)
						(cond 
							(local13
								(LocPrint 15 43)
							)
							(local11
								(LocPrint 15 29)
							)
							(else
								(LocPrint 15 44)
							)
						)
					)
					((Said 'gave,write/ticket')
						(LocPrint 15 45)
					)
					((Said 'cross/ave')
						(LocPrint 15 46)
						(ego setMotion: MoveTo 165 150)
					)
					((Said 'get,call,hail/cab')
						(LocPrint 15 47)
					)
					((Said '/bus')
						(LocPrint 15 48)
					)
					((Said 'sat[<down]')
						(LocPrint 15 49)
					)
					((Said 'get,drive/auto')
						(LocPrint 15 50)
					)
					((Said 'frisk/auto')
						(LocPrint 15 51)
					)
					((Said 'open/door[<auto]')
						(if (< (ego x?) 100)
							(LocPrint 15 50)
						else
							(LocPrint 15 52)
						)
					)
					((Said 'open/trunk')
						(if (< (ego x?) 100)
							(LocPrint 15 50)
						else
							(LocPrint 15 44)
						)
					)
					((Said 'break/pane')
						(LocPrint 15 53)
					)
					((Said 'get,buy/flower')
						(if local10
							(LocPrint 15 54)
						else
							(LocPrint 15 55)
						)
					)
					(
						(or
							(Said '[buy,get]/yellow')
							(Said '/1,flower<yellow')
						)
						(if local10
							(LocPrint 15 56)
						else
							(LocPrint 15 44)
						)
					)
					(
						(or
							(Said '[buy,get]/red')
							(Said '/1,flower<red')
						)
						(if local10
							(LocPrint 15 57)
						else
							(LocPrint 15 44)
						)
					)
					((Said '[buy,get]/rose')
						(cond 
							((not (ego has: iFlower))
								(if local10
									(if (ego has: iMoneyClip)
										(if (> dollars rosePrice)
											(= dollars (- dollars rosePrice))
											(LocPrint 15 58)
											(LocPrint 15 59)
											(SolvePuzzle 2 fBoughtFlower)
											(ego get: iFlower)
											((inventory at: iFlower) cel: 1)
											(flowerScript changeState: 2)
										else
											(LocPrint 15 60)
										)
									else
										(LocPrint 15 61)
									)
								else
									(LocPrint 15 55)
								)
							)
							(local10
								(LocPrint 15 62)
							)
							(else
								(LocPrint 15 63)
							)
						)
					)
					((Said '[buy,get]/bouquet,carnation')
						(cond 
							((not (ego has: iFlower))
								(if local10
									(if (> dollars bouquetPrice)
										(= dollars (- dollars bouquetPrice))
										(LocPrint 15 64)
										(LocPrint 15 59)
										(SolvePuzzle 2 fBoughtFlower)
										(ego get: iFlower)
										((inventory at: iFlower) cel: 2)
										(flowerScript changeState: 2)
									else
										(LocPrint 15 60)
									)
								else
									(LocPrint 15 55)
								)
							)
							(local10 (LocPrint 15 62))
							(else (LocPrint 15 63))
						)
					)
					((Said '[buy,get]/plant')
						(cond 
							((not (ego has: iFlower))
								(if local10
									(if (> dollars plantPrice)
										(= dollars (- dollars plantPrice))
										(LocPrint 15 65)
										(LocPrint 15 59)
										(SolvePuzzle 2 fBoughtFlower)
										(ego get: iFlower)
										((inventory at: iFlower) cel: 0)
										(flowerScript changeState: 2)
									else
										(LocPrint 15 60)
									)
								else
									(LocPrint 15 55)
								)
							)
							(local10 (LocPrint 15 62))
							(else (LocPrint 15 63))
						)
					)
					((Said 'use,press/button')
						(if (== (ego onControl: 1) cLCYAN)
							(LocPrint 15 66)
							(= local6 1)
							(lightScript changeState: 1)
							(if (> (ego y?) 170)
								(SolvePuzzle 1 fWaitedToCrossNorth) 
							else
								(SolvePuzzle 1 fWaitedToCrossSouth)
							)
						else
							(LocPrint 15 67)
						)
					)
					((Said 'chat/dude,men,person,crowd')
						(if (ego inRect: 206 110 305 145)
							(LocPrint 15 68)
							(LocPrint 15 69)
						else
							(LocPrint 15 70)
						)
					)
					((Said 'chat/broad,broad,wanda[<flower]')
						(cond 
							((not local8)
								(LocPrint 15 71)
							)
							(local9
								(LocPrint 15 70)
							)
							(local11
								(LocPrint 15 29)
							)
							(else
								(Print 15 27 #at -1 15)
							)
						)
					)
					(
						(or
							(Said 'much<how[/flower]')
							(Said 'are/flower')
							(Said 'gave,tell,ask[/i,broad,wanda]/cost')
							(Said 'gave,tell,ask/cost')
						)
						(if local10
							(LocPrint 15 72)
						else
							(LocPrint 15 73)
						)
					)
					((Said 'pay')
						(if local10
							(if (ego has: iFlower)
								(LocPrint 15 74)
							else
								(LocPrint 15 75)
							)
						else
							(LocPrint 15 76)
						)
					)
					((Said 'affirmative')
						(if local10
							(LocPrint 15 54)
						else
							(LocPrint 15 77)
						)
					)
					((Said 'no')
						(if local10
							(LocPrint 15 78 25 4)
							(LocPrint 15 79)
							(flowerScript changeState: 2)
						else
							(LocPrint 15 80)
						)
					)
					((Said 'ask/name[<broad]')
						(cond 
							(local10
								(LocPrint 15 81)
							)
							(local11
								(LocPrint 15 29)
							)
							(local9
								(LocPrint 15 82)
							)
							(else
								(LocPrint 15 31)
							)
						)
					)
					((Said 'kiss/broad,wanda')
						(cond 
							(local10
								(LocPrint 15 83)
							)
							(local9
								(LocPrint 15 84)
							)
							(local11
								(LocPrint 15 85)
							)
							(else
								(LocPrint 15 86)
							)
						)
					)
					(
						(or
							(Said 'fuck,eat,blow/broad,wanda')
							(Said 'feel/boob,cunt')
						)
						(cond 
							(local10
								(LocPrint 15 87)
								(= local13 1)
								(flowerScript changeState: 2)
							)
							(local9
								(LocPrint 15 84)
							)
							(local11
								(LocPrint 15 85)
							)
							(else
								(LocPrint 15 86)
							)
						)
					)
					((Said 'frisk/broad,wanda')
						(cond 
							(local10
								(LocPrint 15 88)
							)
							(local9
								(LocPrint 15 84)
							)
							(local11
								(LocPrint 15 85)
							)
							(else
								(LocPrint 15 86)
							)
						)
					)
					((Said 'arrest/broad,wanda')
						(cond 
							(local10
								(LocPrint 15 89)
							)
							(local9
								(LocPrint 15 84)
							)
							(local11
								(LocPrint 15 85)
							)
							(else
								(LocPrint 15 86)
							)
						)
					)
				)
			)
		)
	)
)

(instance lightScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((> local0 1)
				(-- local0)
			)
			((== local0 1)
				(if (cast contains: keith)
					(if (not (if (> 176 (keith y?)) (> (keith y?) 133)))
						(self changeState: 2)
					)
				else
					(self changeState: 2)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(lightPole setCel: 5)
				(= local0 100)
			)
			(2
				(= local6 0)
				(= local0 0)
				(lightPole setCel: 6)
				(if
					(and
						(> 176 (ego y?))
						(> (ego y?) 142)
					)
					(Print 15 90 #time 2)
					(= local7 1)
				)
			)
		)
	)
)

(instance flowerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(flowerGirl
					view: 43
					posn: -15 120
					init:
					setCycle: Walk
					setAvoider: (Avoider new:)
					setMotion: Chase ego 25
					illegalBits: -31744
					startUpd:
				)
				(flowerSounds play:)
				(= local8 1)
				(= local9 1)
			)
			(1
				(LocPrint 15 91)
			)
			(2
				(= local11 1)
				(= local10 0)
				(flowerGirl setMotion: MoveTo -15 120 self)
			)
			(3
				(flowerGirl stopUpd:)
				(= local12 1)
				(= local8 0)
				(= local11 0)
			)
		)
	)
)
