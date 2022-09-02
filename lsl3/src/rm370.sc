;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm370 0
)

(local
	inputNum1
	inputNum2
	inputNum3
	leaveMsg
	distEgoToLocker
	[str 200]
)
(instance rm370 of Room
	(properties
		picture 370
		horizon 54
	)
	
	(method (init)
		(Load VIEW (+ 706 larryBuffed))
		(Load VIEW (+ 702 larryBuffed))
		(Load VIEW (+ 704 larryBuffed))
		(Load VIEW (+ 700 larryBuffed))
		(if (ego has: 8)
			(Load VIEW (+ 703 larryBuffed))
			(Load VIEW 8)
		)
		(super init:)
		(if (> machineSpeed 16)
			(aMan1 init:)
			(aMan2 init:)
			(aMan3 init:)
		)
		(aLocker init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 375)
				(ego loop: 2 posn: 221 58)
			)
			((== prevRoomNum 380)
				(ego loop: 2 posn: 313 62)
			)
			(else
				(= currentStatus egoLEISURESUIT)
				(ego posn: 307 179)
			)
		)
		(ego
			view:
				(switch currentStatus
					(egoNAKED_CENSORED (+ 706 larryBuffed))
					(egoNAKED (+ 702 larryBuffed))
					(egoTOWEL (+ 703 larryBuffed))
					(egoSWEATSUIT (+ 704 larryBuffed))
					(else  (+ 700 larryBuffed))
				)
			init:
		)
	)
	
	(method (newRoom n)
		(if (< (aLocker y?) 999)
			(Bset fLockerRobbed)
		)
		(if
			(and
				(== n 375)
				(== currentStatus egoTOWEL)
				(ego has: iTowel)
			)
			(Print 370 0
				#at 10 -1
				#width 290
			)
			(PutInRoom iTowel 375)
		)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(switch currentStatus
			(egoNAKED
				(ego
					observeControl: cLRED cLMAGENTA
					ignoreControl: cYELLOW
				)
			)
			(egoNAKED_CENSORED
				(ego
					observeControl: cLRED cLMAGENTA
					ignoreControl: cYELLOW
				)
			)
			(egoSWEATSUIT
				(ego
					observeControl: cYELLOW cLRED
					ignoreControl: cLMAGENTA
				)
			)
			(egoTOWEL
				(ego
					observeControl: cLMAGENTA cLRED
					ignoreControl: cYELLOW
				)
			)
			(else 
				(ego
					observeControl: cLMAGENTA cYELLOW
					ignoreControl: cLRED
				)
			)
		)
		(if (& (ego onControl:) cLCYAN)
			(ego setPri: 3)
		)
		(if (& (ego onControl:) cLGREEN)
			(ego setPri: -1)
		)
		(cond 
			((& (ego onControl:) cGREEN)
				(curRoom newRoom: 375)
			)
			((& (ego onControl:) cBLUE)
				(if (not leaveMsg)
					(cond 
						((== currentStatus egoLEISURESUIT)
							(= leaveMsg TRUE)
							(Print 370 1)
						)
						((== currentStatus egoSWEATSUIT)
							(= leaveMsg TRUE)
							(Print 370 2)
						)
					)
				)
			)
			((& (ego onControl:) cRED)
				(curRoom newRoom: 380)
			)
			((& (ego onControl:) cCYAN)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
							(= leaveMsg TRUE)
							(Print 370 3)
						)
						((== currentStatus egoLEISURESUIT)
							(= leaveMsg TRUE)
							(Print 370 4)
							(if (not larryBuffed)
								(Print 370 5 #at -1 144)
							)
						)
						((== currentStatus egoTOWEL)
							(= leaveMsg TRUE)
							(Print 370 6)
						)
					)
				)
			)
			((== EAST (ego edgeHit?))
				(= currentStatus 0)
				(= currentEgoView (+ 700 larryBuffed))
				(curRoom newRoom: 360)
			)
			((& (ego onControl:) cMAGENTA)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
							(= leaveMsg TRUE)
							(Print 370 7)
						)
						((== currentStatus egoSWEATSUIT)
							(= leaveMsg TRUE)
							(Print 370 8)
						)
						((== currentStatus egoTOWEL)
							(= leaveMsg TRUE)
							(Print 370 9)
						)
					)
				)
			)
			(else (= leaveMsg FALSE))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setPri:
					setLoop: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 20) self
				)
			)
			(2
				(= seconds 2)
			)
			(3
				(ego
					setMotion: MoveTo (ego x?) (- (ego y?) 20)
					view:
						(switch currentStatus
							(egoNAKED_CENSORED (+ 706 larryBuffed))
							(egoNAKED (+ 702 larryBuffed))
							(egoTOWEL (+ 703 larryBuffed))
							(egoSWEATSUIT (+ 704 larryBuffed))
							(else  (+ 700 larryBuffed))
						)
				)
				(= cycles 22)
			)
			(4
				(Print @str)
				(NormalEgo loopE (ego view?))
			)
		)
	)

	(method (handleEvent event)
		;EO: This method has been successfully decompiled!
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said '/combination')
					(Said 'affirmative')
					(Said 'unbolt,use,open/locker,door')
				)
				(if (< (aLocker y?) 999)
					(ItIs)
				else
					(Print 370 10)
					;EO: this input was the undecompilable part.
					; It works without issue.
					(while (<= inputNum1 0)
						(= inputNum1 (GetNumber {First number:}))
					)
					(while (<= inputNum2 0)
						(= inputNum2 (GetNumber {Second number:}))
					)
					(while (<= inputNum3 0)
						(= inputNum3 (GetNumber {Third number:}))
					)
					(Printf 370 11 inputNum1 inputNum1 inputNum2 inputNum3)
					(cond 
						((not (& (ego onControl:) cBROWN))
							(Print 370 12)
						)
						(
							(or
								(!= inputNum1 lockerNum1)
								(!= inputNum2 lockerNum2)
								(!= inputNum3 lockerNum3)
							)
							(Print 370 13)
						)
						(else
							(if (not (Btst fUnlockedLocker))
								(Bset fUnlockedLocker)
								(theGame changeScore: 100)
								(Print 370 14)
							else
								(Print 370 15)
							)
							(aLocker posn: 88 65)
						)
					)
					(= inputNum1 0)
					(= inputNum2 0)
					(= inputNum3 0)
				)
			)
			(
				(or
					(Said 'unknownnumber/')
					(Said '/unknownnumber')
					(Said '//unknownnumber')
				)
				(Print 370 16)
			)
			((Said 'bolt,close/locker,door')
				(cond 
					((not (< (aLocker y?) 999))
						(ItIs)
					)
					((not (& (ego onControl:) cBROWN))
						(NotClose)
					)
					(else
						(Ok)
						(aLocker posn: 1111 1111)
					)
				)
			)
			((Said 'drain,get/art')
				(Print 370 17)
				(Print 370 18 #at -1 144)
			)
			(
				(or
					(Said 'naked')
					(Said 'wear/nothing')
					(Said 'get/naked')
					(Said 'naked')
					(Said '(change<(out<of),from),(get<off),remove/cloth,towel,sweats,cloth')
				)
				(cond 
					((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
						(Print 370 19)
					)
					((not (& (ego onControl:) cBROWN))
						(Print 370 20)
					)
					((not (< (aLocker y?) 999))
						(Print 370 21)
					)
					(else
						(Format @str 370 22)
						(if (< filthLevel 3)
							(= currentStatus egoNAKED_CENSORED)
						else
							(= currentStatus egoNAKED)
						)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said 'dress<get')
					(Said 'get/dress')
					(Said 'wear,get,(alter<in,to),(put<on)/cloth[<leisure]')
				)
				(cond 
					((== currentStatus egoLEISURESUIT)
						(Print 370 23)
					)
					((Btst fWet)
						(Print 370 24)
					)
					((not (& (ego onControl:) cBROWN))
						(Print 370 25)
					)
					((not (< (aLocker y?) 999))
						(Print 370 21)
					)
					((Btst fLockerRobbed)
						(Print 370 26)
					)
					(else
						(Format @str 370 27)
						(= currentStatus egoLEISURESUIT)
						(self changeState: 1)
					)
				)
			)
			((Said 'wear,get,(alter<in),(put<on)/towel')
				(cond 
					((== currentStatus egoTOWEL)
						(Print 370 28)
					)
					((not (ego has: iTowel))
						(DontHave)
					)
					((not (& (ego onControl:) cBROWN))
						(Print 370 29)
					)
					((not (< (aLocker y?) 999))
						(Print 370 21)
					)
					((Btst fLockerRobbed)
						(Print 370 26)
					)
					(else
						(Format @str 370 30)
						(= currentStatus egoTOWEL)
						(self changeState: 1)
					)
				)
			)
			(
				(Said 'wear,get,(alter<in),(put<on)/sweats,(suit<sweat)')
				(cond 
					((== currentStatus egoSWEATSUIT)
						(Print 370 31)
					)
					((Btst fWet)
						(Print 370 32)
					)
					((not (& (ego onControl:) cBROWN))
						(Print 370 25)
					)
					((not (< (aLocker y?) 999))
						(Print 370 21)
					)
					((Btst fLockerRobbed)
						(Print 370 26)
					)
					(else
						(Format @str 370 33)
						(= currentStatus egoSWEATSUIT)
						(if (not (Btst fWoreSweatsuit))
							(Bset fWoreSweatsuit)
							(theGame changeScore: 4)
						)
						(self changeState: 1)
					)
				)
			)
			(
				(or
					(Said '(look<for),find/locker,69')
					(Said '(look<for),find//locker,69')
				)
				(cond 
					(
						(>
							(= distEgoToLocker (GetDistance (ego x?) (ego y?) 88 65))
							150
						)
						(Print 370 34)
					)
					((> distEgoToLocker 80)
						(Print 370 35)
					)
					((& (ego onControl:) cLBLUE)
						(Print 370 36)
					)
					((& (ego onControl:) cGREY)
						(Print 370 37)
					)
					((& (ego onControl:) cLGREY)
						(Print 370 38)
					)
					((& (ego onControl:) cBROWN)
						(Print 370 39)
					)
					(else
						(Print 370 40)
					)
				)
			)
			((Said 'pick/bolt,locker,69')
				(Print 370 41)
			)
			((Said 'caress/locker,top,bay')
				(Print 370 42)
			)
			(
			(Said '(look<in),explore,(look<in)/locker,(door<locker)')
				(cond 
					((not (& (ego onControl:) cBROWN))
						(Print 370 43)
					)
					((not (< (aLocker y?) 999))
						(Print 370 44)
					)
					((Btst fLockerRobbed)
						(Print 370 45)
						(Print 370 46)
						(Print 370 47)
					)
					((== currentStatus egoLEISURESUIT)
						(Print 370 48)
					)
					((== currentStatus egoTOWEL)
						(Print 370 49)
					)
					((== currentStatus egoSWEATSUIT)
						(Print 370 50)
					)
					((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
						(Print 370 49)
					)
				)
			)
			((Said 'get,spray,wear,use/can,spray,deodorant')
				(cond 
					((not (& (ego onControl:) cBROWN))
						(Print 370 51)
					)
					((not (< (aLocker y?) 999))
						(Print 370 21)
					)
					(
						(or
							(== currentStatus egoNAKED)
							(== currentStatus egoNAKED_CENSORED)
							(== currentStatus egoTOWEL)
						)
						(Print 370 52)
						(Print 370 52)
						(Bclr fNotUseDeodorant)
						(if (not (Btst fUsedDeodorant))
							(Bset fUsedDeodorant)
							(theGame changeScore: 27)
						)
						(Print 370 53)
					)
					(else
						(Print 370 54
							#at -1 144
						)
						(theGame changeScore: -1)
					)
				)
			)
			(
				(or
					(Said 'dry,dry[/body,i]')
					(Said 'caress/self,i')
					(Said 'dry')
					(Said 'use,(dry<with),(dry<off)/towel')
				)
				(cond 
					((not (ego has: iTowel))
						(Print 370 55)
					)
					(
						(and
							(!= currentStatus egoTOWEL)
							(!= currentStatus egoNAKED_CENSORED)
							(!= currentStatus egoNAKED)
						)
						(Print 370 56)
					)
					((not (Btst fWet))
						(Print 370 57)
					)
					((and (!= currentStatus egoTOWEL) (not (< (aLocker y?) 999)))
						(Print 370 58)
					)
					(else
						(Bclr fWet)
						(if (not (Btst fDriedWithTowel))
							(Bset fDriedWithTowel)
							(theGame changeScore: 22)
						)
						(Print 370 59
							#icon 8 0 0
						)
					)
				)
			)
			((Said 'address')
				(Print 370 60)
			)
			(
				(or
					(Said '/combination')
					(Said 'are<where,what/combination,locker')
				)
				(Print 370 61)
			)
			((Said 'look>')
				(cond 
					((Said '/man')
						(Print 370 62)
					)
					((Said '/door,door')
						(cond 
							((& (ego onControl:) cBLUE)
								(Print 370 63)
							)
							((& (ego onControl:) cCYAN)
								(Print 370 64)
								(Print 370 65)
								(Print 370 66)
							)
							(else
								(Print 370 67)
								(Print 370 68)
							)
						)
					)
					((and (< (aLocker y?) 999) (Said '/art'))
						(Print 370 69)
					)
					((Said '/number')
						(if
							(or
								(& (ego onControl:) cBROWN)
								(& (ego onControl:) cLGREY)
							)
							(Print 370 70)
						else
							(Printf 370 71 (Random 1 999))
							(Print 370 72 #at -1 144)
						)
					)
					((Said '/69,(locker<69)')
						(Print 370 73)
					)
					((Said '/locker<my')
						(Print 370 61)
					)
					((Said '/locker,bay')
						(Print 370 74)
					)
					((Said '/sweatpants')
						(if (!= currentStatus egoSWEATSUIT)
							(DontHave)
						else
							(Print 370 75)
						)
					)
					((Said '/deodorant,can,spray')
						(Print 370 76)
					)
					((Said '[/area]')
						(Print 370 77)
					)
				)
			)
			((or (Said '/69') (Said '//69'))
				(Print 370 78)
			)
		)
	)
)

(instance aLocker of View
	(properties
		y 1111
		x 1111
		view 370
		loop 2
	)
)

(instance aMan1 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random 80 200) 14
			setStep: 1 1
			setCycle: Walk
			setScript: Man1Script
		)
	)
)

(instance Man1Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan1 setMotion: MoveTo (Random 81 200) 14 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan1 setMotion: MoveTo (Random 80 100) 14 self)
				(= state -1)
			)
		)
	)
)

(instance aMan2 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 1) 8
			setStep: 1 1
			setCycle: Walk
			setScript: Man2Script
		)
	)
)

(instance Man2Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan2 setMotion: MoveTo (Random 2 40) 8 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan2 setMotion: MoveTo (Random -60 1) 8 self)
				(= state -1)
			)
		)
	)
)

(instance aMan3 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 0) 20
			setStep: 1 1
			setCycle: Walk
			setScript: Man3Script
		)
	)
)

(instance Man3Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan3 setMotion: MoveTo (Random 2 22) 20 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan3 setMotion: MoveTo (Random -60 1) 20 self)
				(= state -1)
			)
		)
	)
)
