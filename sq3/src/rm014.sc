;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm014 0
)

(local
	lclWindow
	local1
	local2
	thePlanet
)
(procedure (StandUp)
	(pilot loop: 1 setPri: 15 posn: 150 189 stopUpd:)
	(= sittingInCockpit FALSE)
)

(procedure (SitDown)
	(pilot loop: 0 setPri: 10 posn: 161 114 stopUpd:)
	(= sittingInCockpit TRUE)
)

(instance rm014 of Room
	(properties
		picture 14
		style HSHUTTER
	)
	
	(method (init &tmp [str 150])
		(if
		(not (if (and (== global176 5) global179) global181))
			(if
				(not
					(if (and (== global206 2) (== global207 2))
						(== prevRoomNum 17)
					)
				)
				(self setRegions: 701)
			)
		)
		(Load VIEW 31)
		(Load VIEW 28)
		(Load VIEW 65)
		(Load SOUND 31)
		(Load SOUND 33)
		(Load SOUND 59)
		(Load SOUND 83)
		(pilot init:)
		(super init:)
		(if (!= prevRoomNum 31)
			(TheMenuBar draw:)
			(StatusLine enable:)
		)
		(switch prevRoomNum
			(2
				(wallA init: setLoop: 4 setCel: 0)
				(SitDown)
			)
			(8
				(wallA init: setLoop: 1 setCel: 0)
				(wallB init: setLoop: 1 setCel: 1)
				(wallC init: setLoop: 1 setCel: 2)
				(StandUp)
			)
			(16
				(StandUp)
				(cond 
					((== shipLocation shipJUNKBAY)
						(wallA init: setLoop: 1 setCel: 0)
						(wallB init: setLoop: 1 setCel: 1)
						(wallC init: setLoop: 1 setCel: 2)
					)
					(
						(or
							(== shipLocation shipSPACE)
							(== shipLocation shipMONOLITH)
							(== shipLocation shipORTEGA_ORBIT)
							(== shipLocation shipPESTULON_ORBIT)
							(== shipLocation shipPHLEEBHUT_ORBIT)
						)
						(wallA init: setLoop: 4 setCel: 0)
					)
					((== shipLocation shipORTEGA_LAND) (wallA init: setLoop: 2 stopUpd:))
					((== shipLocation shipPHLEEBHUT_LAND) (wallA init: setLoop: 3 stopUpd:))
					((== shipLocation shipPESTULON_LAND) (wallA init: setLoop: 5 stopUpd:))
				)
				(if (== global209 6)
					(Load SOUND 69)
					(TheMenuBar hide:)
					(StatusLine disable:)
					(lp1 init:)
					(lp2 init:)
					(lp3 init:)
					(lp4 init:)
					(theMusic number: 69 loop: -1 play:)
				)
			)
			(17
				(SitDown)
				(cond 
					((== shipLocation shipJUNKBAY)
						(wallA init: setLoop: 1 setCel: 0)
						(wallB init: setLoop: 1 setCel: 1)
						(wallC init: setLoop: 1 setCel: 2)
					)
					(
						(or
							(== shipLocation shipSPACE)
							(== shipLocation shipMONOLITH)
							(== shipLocation shipORTEGA_ORBIT)
							(== shipLocation shipPESTULON_ORBIT)
							(== shipLocation shipPHLEEBHUT_ORBIT)
						)
						(wallA init: setLoop: 4 setCel: 0)
					)
					((== shipLocation shipORTEGA_LAND) (wallA init: setLoop: 2 stopUpd:))
					((== shipLocation shipPHLEEBHUT_LAND) (wallA init: setLoop: 3 stopUpd:))
					((== shipLocation shipPESTULON_LAND) (wallA init: setLoop: 5 stopUpd:))
				)
				(if
					(and
						(== shipLocation shipSPACE)
						(or (== global209 2) (== global209 3))
					)
					(if
					(not (if (== scanningSector 69) (== currentSector 82)))
						(if
						(not (if (== scanningSector 82) (== currentSector 69)))
							(if
								(and
									(not (if (and (== global176 5) global179) global181))
									(not twoGuysOnBoard)
								)
								(RedrawCast)
								(if (== global209 2) (Print 14 0) else (Print 14 1))
							)
						)
					)
				)
				(if (== global209 6)
					(Load SOUND 69)
					(TheMenuBar hide:)
					(StatusLine disable:)
					(lp1 init:)
					(lp2 init:)
					(lp3 init:)
					(lp4 init:)
					(SitDown)
					(theMusic number: 69 loop: -1 play:)
				)
			)
			(20
				(wallA init: setLoop: 4 setCel: 0)
				(SitDown)
				(= shipLocation 1)
				(= global207 1)
			)
			(21
				(SitDown)
				(cond 
					((== shipLocation shipORTEGA_LAND) (wallA init: setLoop: 2 stopUpd:) (= thePlanet {Ortega}))
					((== shipLocation shipPHLEEBHUT_LAND) (wallA init: setLoop: 3 stopUpd:) (= thePlanet {Phleebhut}))
					((== shipLocation shipPESTULON_LAND) (wallA init: setLoop: 5 stopUpd:) (= thePlanet {Pestulon}))
				)
				(RedrawCast)
				(theMusic stop:)
				(powerDown play:)
				(= global208 0)
				(ShakeScreen 10 3)
				(Print (Format @str 14 2 thePlanet))
			)
			(28
				(wallA init: setLoop: 4 setCel: 0)
				(SitDown)
				(= enterpriseLeftMonolithBurger TRUE)
				(= shipLocation shipMONOLITH)
			)
			(31
				(if (== global209 6)
					(Load SOUND 69)
					(TheMenuBar hide:)
					(StatusLine disable:)
					(lp1 init:)
					(lp2 init:)
					(lp3 init:)
					(lp4 init:)
					(SitDown)
					(theMusic number: 69 loop: -1 play:)
				else
					(SitDown)
					(wallA init: setLoop: 4 setCel: 0)
					(RedrawCast)
					(if (== global209 2) (Print 14 0) else (Print 14 1))
				)
			)
			(49
				(wallA init: setLoop: 3 stopUpd:)
				(StandUp)
				(theMusic fade:)
				(= local1 1)
				(= shipLocation shipPHLEEBHUT_LAND)
				(= global206 0)
				(self setScript: rampScript)
			)
			(62
				(wallA init: setLoop: 2 stopUpd:)
				(StandUp)
				(theMusic fade:)
				(= local1 1)
				(= shipLocation shipORTEGA_LAND)
				(= global206 0)
				(self setScript: rampScript)
			)
			(80
				(wallA init: setLoop: 5 stopUpd:)
				(StandUp)
				(theMusic fade:)
				(= local1 1)
				(= shipLocation shipPESTULON_LAND)
				(= global206 0)
				(self setScript: rampScript)
			)
			(94
				(= twoGuysOnBoard 1)
				(self setScript: (ScriptID 22 0))
				(= global102 22)
				(wallA init: setLoop: 4 setCel: 0)
				(SitDown)
				(Print 14 3)
				(= global175 60)
				(= global218 1)
				(= shipLocation shipPESTULON_ORBIT)
				(= global206 3)
				(= global179 1)
				(= global208 2)
			)
		)
		(if (and twoGuysOnBoard (!= prevRoomNum 94))
			(if (and (== global176 5) global179 global181)
				(self setScript: (ScriptID 24 0))
				(= global102 24)
			else
				(self setScript: (ScriptID 23 0))
				(= global102 23)
			)
		)
		(if
		(or (not (InRoom iReactor)) (not (InRoom iWire)))
			(floorPanel init:)
		)
		(if
			(and
				(or (== global206 0) (== global206 3))
				(!= global102 24)
			)
			(HandsOn)
		)
		(= programControl FALSE)
		(= inCartoon FALSE)
	)
	
	(method (doit)
		(if (and global167 (not script))
			(self setScript: arrivalScript)
		)
		(if global258 (self newRoom: 99))
		(super doit:)
	)
	
	(method (dispose)
		(if lclWindow (lclWindow dispose:))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(mouseDown
				(cond 
					((and (== sittingInCockpit 0) (not isHandsOff))
						(cond 
							(
								(and
									(<= 143 (event x?))
									(<= (event x?) 180)
									(<= 63 (event y?))
									(<= (event y?) 117)
								)
								(event claimed: TRUE)
								(SitDown)
							)
							(
								(and
									(<= 263 (event x?))
									(<= (event x?) 319)
									(<= 59 (event y?))
									(<= (event y?) 142)
								)
								(event claimed: TRUE)
								(cls)
								(curRoom newRoom: 16)
							)
							(
								(and
									(== shipLocation 0)
									(<= 105 (event x?))
									(<= (event x?) 215)
									(<= 0 (event y?))
									(<= (event y?) 17)
								)
								(event claimed: 1)
								(curRoom newRoom: 8)
							)
							(
								(and
									(<= 276 (event x?))
									(<= (event x?) 301)
									(<= 160 (event y?))
									(<= (event y?) 177)
								)
								(event claimed: TRUE)
								(cls)
								(if (== sittingInCockpit FALSE)
									(cond 
										(
											(or
												(== shipLocation 5)
												(== shipLocation 6)
												(== shipLocation 8)
											)
											(self setScript: rampScript)
										)
										((== shipLocation 0) (Print 14 4) (curRoom newRoom: 8))
										(else (Print 14 5))
									)
								else
									(Print 14 6)
								)
							)
						)
					)
					(
						(and
							(== sittingInCockpit TRUE)
							(not isHandsOff)
							(not (== global209 6))
							(<= 105 (event x?))
							(<= (event x?) 215)
							(<= 131 (event y?))
							(<= (event y?) 189)
						)
						(event claimed: TRUE)
						(StandUp)
					)
				)
			)
			(saidEvent
				(cond 
					((Said '/motivator') (Print 14 7))
					(
						(Said
							'press,disembark,disembark,open,(get<out)[/ramp,door,button,craft]'
						)
						(if (== sittingInCockpit 0)
							(cond 
								(
									(or
										(== shipLocation 5)
										(== shipLocation 6)
										(== shipLocation 8)
									)
									(self setScript: rampScript)
								)
								((== shipLocation 0) (Print 14 4) (curRoom newRoom: 8))
								(else (Print 14 5))
							)
						else
							(Print 14 6)
						)
					)
					((Said 'close/door,door,ramp') (Print 14 8))
					(
						(or
							(Said '(turn<on),use,begin,fly/engine,craft')
							(Said 'launch,land,park[/craft]')
							(Said 'shoot[/gun]')
						)
						(Print 14 9)
					)
					(
					(Said 'enter,go,(sit[<down,in]),get/chair<passenger')
						(cond 
							((== sittingInCockpit TRUE) (Print 14 10))
							(twoGuysOnBoard (Print 14 11))
							(else (Print 14 12))
						)
					)
					(
						(or
							(Said 'sit[<down]')
							(Said 'enter,go,(sit[<down,in]),get/chair,cabin')
						)
						(if (== sittingInCockpit TRUE)
							(Print 14 10)
						else
							(SitDown)
						)
					)
					(
						(or
							(Said '(get<up),stand[<up]')
							(Said 'disembark/chair,cabin')
						)
						(cond 
							((== sittingInCockpit FALSE) (Print 14 13))
							((== global209 6) (Print 14 14))
							(else (StandUp))
						)
					)
					((Said 'use,wear,drop[<on]/panties')
						(cond 
							((not (ego has: iThermoUnderwear)) (Print 14 15))
							(wearingUnderwear (Print 14 16))
							((== sittingInCockpit TRUE) (Print 14 17))
							(else
								(Print 14 18)
								(theGame changeScore: 10)
								(= wearingUnderwear TRUE)
							)
						)
					)
					((Said 'remove,get[<off]/panties')
						(cond 
							((not (ego has: iThermoUnderwear)) (Print 14 15))
							(wearingUnderwear (Print 14 19))
							(else (Print 14 20))
						)
					)
					((Said 'explore/cushion,chair')
						(cond 
							((== sittingInCockpit FALSE) (Print 14 21))
							((not searchedPilotSeat)
								(Print 14 22)
								(theGame changeScore: 10)
								(= buckazoids (+ buckazoids 7))
								(ego get: iBuckazoids)
								(= searchedPilotSeat TRUE)
							)
							(else (Print 14 23))
						)
					)
					(
						(Said
							'remove,hoist,open,get/tile,compartment,(console[<video])'
						)
						(if (!= shipRepairLevel 4)
							(Print 14 24)
						else
							(Print 14 25)
						)
					)
					(
					(Said 'close/tile,compartment,deck,(console[<video])')
						(if (!= shipRepairLevel 4)
							(Print 14 26)
						else
							(Print 14 27)
						)
					)
					((Said 'converse/man,man') (if twoGuysOnBoard (Print 14 28) else (Print 14 29)))
					((Said 'look>')
						(cond 
							((Said '/cavity,compartment')
								(switch shipRepairLevel
									(4 (Print 14 27))
									(3 (Print 14 30))
									(2 (Print 14 31))
									(else  (Print 14 32))
								)
							)
							((Said '/tile')
								(if (!= shipRepairLevel 4)
									(Print 14 33)
								else
									(Print 14 34)
								)
							)
							((or (Said '/deck') (Said '<down'))
								(if (!= shipRepairLevel 4)
									(Print 14 35)
								else
									(Print 14 36)
								)
							)
							((Said '/console')
								(cond 
									((== sittingInCockpit TRUE) (Print 14 37))
									((<= shipRepairLevel 1) (Print 14 38))
									((== shipRepairLevel 3) (Print 14 30))
									((== shipRepairLevel 2) (Print 14 31))
									(else (Print 14 39))
								)
							)
							((Said '/comp<access')
								(cond 
									((== global209 6) (Print 14 40))
									((== sittingInCockpit 0) (curRoom newRoom: 16))
									(else (Print 14 41))
								)
							)
							(
								(or
									(Said '/(comp[<pilot]),control')
									(Said '/comp[<comp]')
								)
								(cond 
									((== global209 6) (Print 14 40))
									((== sittingInCockpit FALSE) (cls) (curRoom newRoom: 16))
									((and (InRoom iReactor) (InRoom iWire)) (cls) (curRoom newRoom: 17))
									(else (Print 14 42))
								)
							)
							((Said '/partition') (Print 14 43))
							((Said '/man,prize,scott') (if twoGuysOnBoard (Print 14 44) else (Print 14 45)))
							((Said '/cushion')
								(if (== sittingInCockpit FALSE)
									(Print 14 21)
								else
									(Print 14 46)
								)
							)
							((Said '/cabin')
								(if (== sittingInCockpit TRUE)
									(Print 14 47)
								else
									(Print 14 48)
								)
							)
							((Said '/pane')
								(switch shipLocation
									(0 (Print 14 49))
									(1
										(if (== global209 6) (Print 14 50) else (Print 14 51))
									)
									(2 (Print 14 52))
									(3 (Print 14 53))
									(4 (Print 14 54))
									(7 (Print 14 55))
									(5 (Print 14 56))
									(6 (Print 14 57))
									(8 (Print 14 58))
									(else  (Print 14 59))
								)
							)
							((Said '/chair<passenger') (if twoGuysOnBoard (Print 14 11) else (Print 14 12)))
							((Said '/chair')
								(cond 
									((== sittingInCockpit TRUE) (Print 14 60))
									(twoGuysOnBoard (Print 14 11))
									(else (Print 14 12))
								)
							)
							((Said '/star,eva')
								(if (== shipLocation 1)
									(Print 14 61)
								else
									(Print 14 62)
								)
							)
							((or (Said '/ceiling') (Said '<up'))
								(cond 
									((== sittingInCockpit 0) (Print 14 63))
									((== shipLocation 0) (Print 14 64))
									(else (Print 14 65))
								)
							)
							((Said '/ramp') (Print 14 66))
							((Said '/door') (Print 14 67))
							((Said '/button') (Print 14 68))
							((or (Said '/hal[<cable]') (Said 'cable'))
								(if (cast contains: floorPanel)
									(Print 14 69)
								else
									(Print 14 70)
								)
							)
							((Said '[<around,at,in][/area,craft]')
								(cond 
									((== sittingInCockpit TRUE) (Print 14 47))
									((not examinedMallard) (= examinedMallard TRUE) (Print 14 71))
									(else (Print 14 72))
								)
							)
						)
					)
					(
						(or
							(Said 'go,look,(turn<on)/(comp[<pilot]),control')
							(Said '/comp')
						)
						(cond 
							((== sittingInCockpit FALSE) (cls) (curRoom newRoom: 16))
							((and (InRoom iReactor) (InRoom iWire)) (cls) (curRoom newRoom: 17))
							(else (Print 14 42))
						)
					)
					(
						(Said
							'replace,use,fix,place,insert,drop,afix,cable/[/cavity,compartment,deck,console,generator,cable]>'
						)
						(cond 
							((Said '/generator')
								(cond 
									((InRoom iReactor) (Print 14 73))
									((not (ego has: iReactor)) (DontHave))
									((== sittingInCockpit FALSE) (self setScript: reactorScript))
									(else (Print 14 74))
								)
							)
							(
							(or (Said '/cable') (Said '<cable') (Said 'cable<use'))
								(cond 
									((InRoom iWire) (Print 14 73))
									((not (ego has: iWire)) (DontHave))
									((== sittingInCockpit 0) (self setScript: wireScript))
									(else (Print 14 74))
								)
							)
							((or (Said '/crystal') (Said '<crystal'))
								(if (not (ego has: iGlowingGem))
									(DontHave)
								else
									(Print 14 75)
								)
							)
						)
					)
					((Said 'turn,switch[<on]/generator') (Print 14 76))
					((Said 'get/generator')
						(cond 
							((ego has: iReactor) (Print 14 77))
							((InRoom iReactor 14) (Print 14 78))
							(else (Print 14 79))
						)
					)
					((Said 'get/cable') (if (ego has: iWire) (Print 14 77) else (Print 14 78)))
					((Said 'drop,use,afix[<on]/belt')
						(if (== sittingInCockpit TRUE)
							(Print 14 80)
						else
							(Print 14 81)
						)
					)
					((Said 'open/console[<video]')
						(if (!= shipRepairLevel 4)
							(Print 14 82)
						else
							(Print 14 83)
						)
					)
					((Said 'close,replace/console[<video]')
						(if (!= shipRepairLevel 4)
							(Print 14 84)
						else
							(Print 14 8)
						)
					)
					((Said 'go/compartment,cavity') (Print 14 85))
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if global102
			((ScriptID global102 0) dispose:)
			(DisposeScript 22)
			(DisposeScript 23)
			(DisposeScript 24)
		)
		(if (== (theMusic number?) 69) (theMusic fade:))
		(super newRoom: newRoomNumber)
	)
)

(instance reactorScript of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(pilot loop: 2 forceUpd:)
				(= cycles 15)
			)
			(1
				(if (InRoom iWire)
					(Print 14 86)
					(= shipRepairLevel 4)
					(floorPanel dispose:)
				else
					(Print 14 87)
					(= shipRepairLevel 3)
				)
				((inventory at: iReactor) moveTo: 14)
				(theGame changeScore: 5)
				(= cycles 10)
			)
			(2
				(pilot loop: 1 forceUpd:)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance wireScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(pilot loop: 2 forceUpd:)
				(= cycles 15)
			)
			(1
				(if (InRoom iReactor)
					(Print 14 88)
					(floorPanel dispose:)
					(= shipRepairLevel 4)
				else
					(Print 14 89)
					(= shipRepairLevel 2)
				)
				((inventory at: iWire) moveTo: 14)
				(theGame changeScore: 5)
				(= cycles 10)
			)
			(2
				(pilot loop: 1 forceUpd:)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance rampScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local1
					(ramp
						init:
						setLoop:
							(cond 
								((== shipLocation 5) 2)
								((== shipLocation 6) 3)
								((== shipLocation 8) 5)
							)
						setCel: 1
						stopUpd:
					)
					(= seconds 2)
				else
					(= cycles 10)
				)
			)
			(1
				(if local1
					(ramp dispose:)
					(= local1 0)
					(curRoom setScript: 0)
				else
					(ramp
						init:
						setCel: 1
						setLoop:
							(cond 
								((== shipLocation 5) 2)
								((== shipLocation 6) 3)
								((== shipLocation 8) 5)
							)
						setCel: 1
						stopUpd:
					)
					(= cycles 5)
				)
			)
			(2
				(curRoom
					newRoom:
						(cond 
							((== shipLocation 5) 62)
							((== shipLocation 6) 49)
							((== shipLocation 8) 80)
						)
				)
			)
		)
	)
)

(instance arrivalScript of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= lclWindow (Print 14 90 #at -1 130 #width 280 #dispose))
				(theMusic fade:)
				(= seconds 4)
			)
			(1
				(cls)
				(= lclWindow 0)
				(curRoom newRoom: 17)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(self changeState: 1)
	)
)

(instance wallCScript of Script	
	(method (doit)
		(super doit:)
		(if (== global206 2)
			(if (> (wallA y?) 161) (wallA stopUpd:))
			(if (> (wallB y?) 166) (wallB stopUpd:))
		)
		(if (== global206 1)
			(if (< (wallA y?) 120) (wallA stopUpd:))
			(if (< (wallB y?) 123) (wallB stopUpd:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global206 2)
					(HandsOff)
					(if (== global207 2)
						(wallA dispose:)
						(wallB dispose:)
						(wallC setLoop: 1 setCel: 2 posn: 161 98)
						(RedrawCast)
						(Print 14 91)
						(curRoom setScript: 0)
						(curRoom newRoom: 17)
					else
						(= cycles 3)
					)
				)
				(if (== global206 1) (HandsOff) (self changeState: 2))
			)
			(1
				(thunder play:)
				(ShakeScreen 15)
				(if mallardRisenFromDebris
					(Print 14 92 #at -1 130 #width 280)
				else
					(Print 14 93 #at -1 130 #width 280)
					(= mallardRisenFromDebris TRUE)
				)
				(= cycles 15)
			)
			(2
				(wallC
					setMotion: MoveTo 161 (if (== global206 2) 98 else 67) self
				)
				(wallB
					setMotion: MoveTo 161 (if (== global206 2) 181 else 119)
				)
				(wallA
					setMotion: MoveTo 161 (if (== global206 2) 209 else 118)
				)
			)
			(3
				(if (== global206 2)
					(if global207
						(Print 14 94 #at -1 130 #width 280)
						(= global207 2)
						(curRoom newRoom: 17)
					else
						(blowUp play:)
						(ShakeScreen 30)
						(Print 14 95)
						(EgoDead 0 0 5 7)
					)
				else
					(Print 14 96)
					(wallA stopUpd:)
					(wallB stopUpd:)
					(wallC stopUpd:)
					(= global206 0)
					(HandsOn)
					(= programControl FALSE)
				)
			)
		)
	)
)

(instance pilot of View
	(properties)
	
	(method (init)
		(super init:)
		(self view: 31 ignoreActors: TRUE)
	)
)

(instance floorPanel of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 28
			setLoop: 0
			setCel: 0
			ignoreActors:
			posn: 95 190
			stopUpd:
		)
	)
)

(instance ramp of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 28
			setCel: 1
			setPri: 14
			ignoreActors: TRUE
			posn: 159 189
		)
	)
)

(instance lp1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 0
			ignoreActors: TRUE
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 2
			ignoreActors: TRUE
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp3 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 1
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp4 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 3
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance wallA of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 28
			setCel: 0
			ignoreActors: 1
			x: 161
			y: (if (== global206 1) 210 else 117)
			setPri: 6
			setStep: 1 3
			stopUpd:
		)
	)
)

(instance wallB of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 28
			ignoreActors: 1
			x: 161
			y: (if (== global206 1) 182 else 120)
			setPri: 5
			setStep: 1 2
			stopUpd:
		)
	)
)

(instance wallC of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 28
			ignoreActors: 1
			x: 161
			y: (if (== global206 1) 98 else 67)
			setPri: 4
			setStep: 1 1
			stopUpd:
			setScript: wallCScript
		)
	)
)

(instance blowUp of Sound
	(properties
		number 33
		priority 1
	)
)

(instance thunder of Sound
	(properties
		number 31
		priority 1
	)
)

(instance powerDown of Sound
	(properties
		number 83
		priority 2
	)
)
