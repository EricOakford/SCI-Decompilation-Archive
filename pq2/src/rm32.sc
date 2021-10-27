;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm32 0
)

(local
	newProp
	local1
	local2
	local3
	local4
	local5
	local6
)
(procedure (localproc_000c)
	(Print &rest #at -1 130)
)

(procedure (localproc_001c)
	(Print &rest #at -1 114)
)

(instance mariesTheme of Sound
	(properties
		number 14
	)
)

(instance mariesBeenKidnapped of Sound
	(properties
		number 18
	)
)

(instance rm32 of Room
	(properties
		picture 32
		style $0008
	)
	
	(method (init)
		(super init:)
		(User canInput: 1 canControl: 1)
		(self setLocales: 153)
		(Load rsVIEW 271)
		(= local2 0)
		(= local3 0)
		(= local6 0)
		(if (< gamePhase 8)
			((View new:)
				view: 271
				posn: 252 106
				loop: 1
				cel: 0
				setPri: 8
				init:
				stopUpd:
				addToPic:
			)
			((View new:)
				view: 271
				posn: 257 106
				loop: 0
				cel: 2
				setPri: 8
				init:
				stopUpd:
				addToPic:
			)
			((View new:)
				view: 271
				posn: 164 115
				loop: 0
				cel: 3
				setPri: 9
				init:
				stopUpd:
				addToPic:
			)
		else
			((View new:)
				view: 271
				posn: 274 128
				loop: 1
				cel: 1
				setPri: 9
				init:
				stopUpd:
				addToPic:
			)
			(if (InRoom 13)
				((View new:)
					view: 271
					posn: 247 127
					loop: 0
					cel: 1
					ignoreActors:
					setPri: 8
					init:
					stopUpd:
					addToPic:
				)
				(= local6 1)
			else
				((View new:)
					view: 271
					posn: 247 127
					loop: 0
					cel: 2
					setPri: 8
					init:
					stopUpd:
					addToPic:
				)
				(= local6 0)
			)
			((View new:)
				view: 271
				posn: 113 116
				loop: 0
				cel: 0
				setPri: 9
				init:
				stopUpd:
				addToPic:
			)
		)
		((View new:)
			view: 271
			posn: 35 96
			loop: 2
			cel: 0
			setPri: 6
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 271
			posn: 71 88
			loop: 2
			cel: 1
			setPri: 8
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 271
			posn: 236 87
			loop: 2
			cel: 2
			ignoreActors:
			setPri: 8
			init:
			stopUpd:
			addToPic:
		)
		((= newProp (Prop new:))
			view: 271
			loop: 3
			cel: 0
			posn: 198 105
			init:
			stopUpd:
		)
		(lampBlock
			top: 119
			bottom: 127
			left: 247
			right: 288
			init:
		)
		(self setScript: rm32Script)
	)
)

(instance rm32Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((<= (ego y?) 120)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1) (ego view: (+ (ego view?) 1)))
		)
		(cond 
			(
				(and
					(> (ego y?) 160)
					(cast contains: keith)
					(not local4)
				)
				(= local4 1)
				(ego setMotion: 0)
				(User canControl: 0)
				(keithScript changeState: 6)
			)
			((> (ego y?) 165) (curRoom newRoom: 31))
			(
			(and (ego inRect: 190 95 222 110) (not local1)) (newProp setScript: bathroomScript))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((and (>= gamePhase 8) (== currentCar 13))
						((= keith (Actor new:))
							view: 20
							posn: 255 145
							observeBlocks: lampBlock
							setCycle: Walk
							setStep: 3 2
							setScript: keithScript
							init:
						)
						(ego view: 1 posn: 239 155 observeBlocks: lampBlock init:)
						(mariesBeenKidnapped play:)
					)
					((== prevRoomNum 12)
						(ego init:)
						(if (and (>= gamePhase 8) (== currentCar 13))
							(keith init:)
						)
					)
					(else
						(if (>= gamePhase 8)
							(mariesBeenKidnapped play:)
						else
							(mariesTheme play:)
						)
						(ego
							view: 1
							posn: 240 131
							observeBlocks: lampBlock
							setMotion: MoveTo 184 120
							init:
						)
						(if (and (>= gamePhase 8) (not global127))
							(= global127 1)
							(RedrawCast)
							(localproc_001c 32 0)
							(localproc_000c 32 1)
						)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '<below>')
								(cond 
									((Said '/fridge,oven,cabinet,counter,basin')
										(if (ego inRect: 0 0 172 115)
											(localproc_000c 32 2)
										else
											(localproc_000c 32 3)
										)
									)
									((Said '/bed')
										(if (ego inRect: 215 95 275 111)
											(localproc_000c 32 2)
										else
											(localproc_000c 32 3)
										)
									)
									((Said '/*') (localproc_000c 32 2))
								)
							)
							((Said '[<at,around][/!*,chamber]')
								(cond 
									((ego inRect: 0 0 172 115)
										(if (>= gamePhase 8)
											(localproc_000c 32 4)
										else
											(localproc_000c 32 5)
										)
									)
									((ego inRect: 215 95 275 111) (localproc_000c 32 6))
									((>= gamePhase 8) (localproc_000c 32 7))
									(else (localproc_000c 32 8))
								)
							)
							((Said '/bedroom,(chamber<bed)')
								(if (ego inRect: 215 95 275 111)
									(cond 
										((cast contains: keith) (localproc_000c 32 9))
										((>= gamePhase 8) (localproc_000c 32 10))
										(else (localproc_000c 32 11))
									)
								else
									(localproc_000c 32 12)
								)
							)
							((or (Said '<up') (Said '/ceiling')) (localproc_000c 32 13))
							((Said '/dirt') (localproc_000c 32 14))
							((Said '[<at,down][/floor]')
								(if
									(and
										(not (ego inRect: 0 0 172 115))
										(not (ego inRect: 215 95 275 111))
									)
									(if (>= gamePhase 8)
										(if (ego inRect: 214 110 290 138)
											(localproc_000c 32 15)
										else
											(localproc_000c 32 16)
										)
									else
										(localproc_000c 32 17)
									)
								else
									(localproc_000c 32 17)
								)
							)
							((Said '/couch')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(if (>= gamePhase 8)
										(localproc_000c 32 18)
									else
										(localproc_000c 32 19)
									)
								else
									(localproc_000c 32 20)
								)
							)
							((Said '/wall')
								(localproc_000c 32 21)
								(if (not (ego inRect: 0 0 172 115))
									(localproc_000c 32 22)
								)
							)
							((Said '/hall')
								(if (ego inRect: 215 95 275 111)
									(localproc_000c 32 6)
								else
									(localproc_000c 32 23)
								)
							)
							((Said '/bench')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(localproc_000c 32 24)
								else
									(localproc_000c 32 25)
								)
							)
							((Said '/table')
								(cond 
									((>= gamePhase 8)
										(cond 
											((ego inRect: 152 117 209 132) (localproc_000c 32 26))
											((ego inRect: 214 110 290 138) (localproc_000c 32 27))
											(else (localproc_000c 32 28))
										)
									)
									((ego inRect: 152 117 209 132) (localproc_000c 32 29))
									((ego inRect: 214 110 290 138) (localproc_000c 32 30))
									(else (localproc_000c 32 28))
								)
							)
							((Said '/phone')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(if (>= gamePhase 8)
										(localproc_000c 32 18)
									else
										(localproc_000c 32 31)
									)
								else
									(localproc_000c 32 32)
								)
							)
							((Said '/ashtray,(tray<ash)')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(if (ego inRect: 229 118 265 134)
										(if (>= gamePhase 8)
											(if (not local6)
												(localproc_000c 32 33)
											else
												(localproc_000c 32 34)
											)
										else
											(localproc_000c 32 35)
										)
									else
										(NotClose)
									)
								else
									(localproc_000c 32 36)
								)
							)
							((Said '/light,lamp')
								(cond 
									((ego inRect: 0 0 172 115) (localproc_000c 32 37))
									((ego inRect: 215 95 275 111) (localproc_000c 32 38))
									((and (> (ego x?) 160) (> (ego y?) 112))
										(if (>= gamePhase 8)
											(localproc_000c 32 39)
										else
											(localproc_000c 32 40)
										)
									)
									(else (NotClose))
								)
							)
							((Said '/painting')
								(if (ego inRect: 0 0 172 115)
									(localproc_000c 32 41)
								else
									(localproc_000c 32 42)
								)
							)
							((Said '/plant,flower')
								(cond 
									((ego inRect: 0 0 172 115) (localproc_000c 32 43))
									((ego inRect: 215 95 275 111)
										(if (== ((inventory at: 11) owner?) 30)
											(localproc_000c 32 44)
										else
											(localproc_000c 32 45)
										)
									)
									(else (localproc_000c 32 46))
								)
							)
							((Said '/grass') (localproc_000c 32 47))
							((Said '/pane') (localproc_000c 32 48))
							((Said '/counter')
								(if
									(or
										(ego inRect: 103 113 182 123)
										(ego inRect: 0 0 172 115)
									)
									(localproc_000c 32 49)
								else
									(localproc_000c 32 50)
								)
							)
							((Said '/kitchen')
								(if (ego inRect: 0 0 172 115)
									(if (>= gamePhase 8)
										(localproc_000c 32 4)
									else
										(localproc_000c 32 5)
									)
								else
									(localproc_000c 32 50)
								)
							)
							((Said '/cabinet,basin,oven,fridge')
								(if (ego inRect: 0 0 172 115)
									(localproc_000c 32 51)
								else
									(localproc_000c 32 52)
								)
							)
							((Said '/television,set')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(localproc_000c 32 53)
								else
									(localproc_000c 32 54)
								)
							)
							((Said '/lampshade,(shade<lamp)')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(if (>= gamePhase 8)
										(localproc_000c 32 55)
									else
										(localproc_000c 32 56)
									)
								else
									(NotClose)
								)
							)
							((Said '/bathroom,(chamber<bath)')
								(if (not local1)
									(localproc_000c 32 57)
								else
									(localproc_000c 32 58)
								)
							)
							((Said '/friend')
								(if (cast contains: keith)
									(if
										(and
											(> (keith x?) 215)
											(not (ego inRect: 215 95 275 111))
										)
										(localproc_000c 32 59)
									else
										(localproc_000c 32 60)
									)
								else
									(localproc_000c 32 61)
								)
							)
							((Said '/newspaper,list')
								(if (ego has: 13)
									(localproc_001c 32 62 82 113)
									(if (not local5)
										(= local5 1)
										(localproc_000c 32 63)
										(localproc_000c 32 64)
									)
								else
									(localproc_000c 32 65)
								)
							)
						)
					)
					((Said 'frisk>')
						(cond 
							((Said '/couch')
								(if (and (<= (ego x?) 152) (>= (ego y?) 119))
									(if (not global193)
										(localproc_000c 32 66)
										(localproc_000c 32 67)
										(localproc_000c 32 68)
										(= dollars (+ dollars 2))
										(= global193 1)
									else
										(localproc_000c 32 69)
									)
								else
									(localproc_000c 32 70)
								)
							)
							((Said '/bench')
								(if (ego inRect: 160 117 209 137)
									(if (not global194)
										(localproc_000c 32 71)
										(localproc_000c 32 72)
										(localproc_000c 32 68)
										(= dollars (+ dollars 1))
										(= global194 1)
									else
										(localproc_000c 32 73)
									)
								else
									(localproc_000c 32 74)
								)
							)
							((Said '/drawer,counter,basin,oven,fridge')
								(if (ego inRect: 0 0 172 115)
									(localproc_000c 32 75)
								else
									(localproc_000c 32 50)
								)
							)
							((Said '/bedroom,(chamber<bed)')
								(if (ego inRect: 215 95 275 111)
									(cond 
										((cast contains: keith) (localproc_000c 32 9))
										((>= gamePhase 8) (localproc_000c 32 10))
										(else (localproc_000c 32 76))
									)
								else
									(localproc_000c 32 12)
								)
							)
							((Said '/ashtray,(tray<ash)')
								(if
									(and
										(not (ego inRect: 215 95 275 111))
										(not (ego inRect: 0 0 172 115))
									)
									(if (ego inRect: 229 118 265 134)
										(if (>= gamePhase 8)
											(Print 32 34)
										else
											(localproc_000c 32 77)
										)
									else
										(NotClose)
									)
								else
									(localproc_000c 32 36)
								)
							)
							((Said '/hall')
								(if (>= gamePhase 8)
									(if (ego inRect: 215 95 275 111)
										(localproc_000c 32 78)
									else
										(localproc_000c 32 23)
									)
								else
									(localproc_000c 32 79)
								)
							)
							((Said '/kitchen')
								(if (>= gamePhase 8)
									(if (ego inRect: 215 95 275 111)
										(localproc_000c 32 78)
									else
										(localproc_000c 32 80)
									)
								else
									(localproc_000c 32 79)
								)
							)
							((Said '/bathroom,(chamber<bath)')
								(if (not local1)
									(localproc_000c 32 57)
								else
									(localproc_000c 32 58)
								)
							)
							((Said '/chamber')
								(if (>= gamePhase 8)
									(if (ego has: 13)
										(localproc_000c 32 81)
									else
										(localproc_000c 32 82)
									)
								else
									(localproc_000c 32 79)
								)
							)
							((Said '/*') (localproc_000c 32 83))
						)
					)
					((Said '/cheeks') (localproc_000c 32 84))
					((Said 'enter,go[<in]/bedroom,(chamber<bed)')
						(if (ego inRect: 215 95 275 111)
							(cond 
								((cast contains: keith) (localproc_000c 32 9))
								((>= gamePhase 8) (localproc_000c 32 10))
								(else (localproc_000c 32 11))
							)
						else
							(localproc_000c 32 12)
						)
					)
					((Said 'smell/perfume')
						(if (ego inRect: 215 95 275 111)
							(localproc_000c 32 85)
						else
							(localproc_000c 32 86)
						)
					)
					((Said 'get,pick[<up]>')
						(cond 
							((Said '/ashtray') (localproc_000c 32 87))
							((Said '/table')
								(if
									(or
										(ego inRect: 214 110 290 138)
										(ego inRect: 152 117 209 132)
									)
									(localproc_000c 32 88)
								else
									(localproc_000c 32 28)
								)
							)
							((Said '/newspaper,list')
								(cond 
									((ego has: 13) (localproc_000c 32 89))
									((not local6) (localproc_000c 32 90))
									((not (ego inRect: 229 118 265 134)) (localproc_000c 32 91))
									(else
										((View new:)
											view: 271
											posn: 247 127
											loop: 0
											cel: 2
											setPri: 8
											init:
											stopUpd:
											addToPic:
										)
										(SolvePuzzle 3 91)
										(localproc_000c 32 92 83)
										(localproc_001c 32 62 83 82 113)
										(if (not local5)
											(= local5 1)
											(localproc_000c 32 63 83)
											(localproc_000c 32 64 83)
										)
										(ego get: 13)
										(= local6 0)
									)
								)
							)
							((Said '/painting')
								(if (not (ego inRect: 0 0 172 115))
									(localproc_000c 32 93)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/lamp')
								(if (ego inRect: 214 110 290 138)
									(localproc_000c 32 94)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/plant')
								(if (ego inRect: 214 110 290 138)
									(localproc_000c 32 95)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/phone') (localproc_000c 32 96))
							(
								(Said
									'/couch,bench,television,fridge,oven,cabinet,grass,cabinet'
								)
								(Print 32 97)
							)
						)
					)
					((Said 'move>')
						(cond 
							((Said '/painting')
								(if (ego inRect: 44 118 63 135)
									(localproc_000c 32 93)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/lamp')
								(if (ego inRect: 214 110 290 138)
									(localproc_000c 32 94)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/plant')
								(if (ego inRect: 214 110 290 138)
									(localproc_000c 32 95)
								else
									(localproc_000c 32 91)
								)
							)
							((Said '/couch,bench,television,ashtray,table') (localproc_000c 32 98))
						)
					)
					((Said 'read/newspaper,list')
						(if (ego has: 13)
							(localproc_001c 32 62 82 113)
							(if (not local5)
								(= local5 1)
								(localproc_000c 32 63)
								(localproc_000c 32 64)
							)
						else
							(localproc_000c 32 65)
						)
					)
					((Said 'turn[<on,off]/light,lamp') (localproc_000c 32 99))
					((Said 'chat,call/friend')
						(if (cast contains: keith)
							(localproc_000c 32 100)
						else
							(localproc_000c 32 101)
						)
					)
					((Said 'open/door')
						(if (ego inRect: 190 95 222 110)
							(localproc_000c 32 102)
						else
							(NotClose)
						)
					)
					((Said 'open/fridge,cabinet')
						(if (ego inRect: 0 0 172 115)
							(if (>= gamePhase 8)
								(localproc_000c 32 103)
							else
								(localproc_000c 32 104)
								(localproc_000c 32 105)
							)
						else
							(localproc_000c 32 106)
						)
					)
					(
						(or
							(Said 'get/fingerprint,print')
							(Said 'dust/fingerprint,print')
						)
						(if (ego has: 10)
							(localproc_000c 32 107)
						else
							(localproc_000c 32 108)
						)
					)
					(
						(or
							(Said 'dust>')
							(Said 'powder>')
							(Said 'apply/powder>')
						)
						(cond 
							((not (ego has: 10)) (event claimed: 1) (localproc_000c 32 108))
							(
							(Said '/table,couch,bench,counter,cabinet,phone,ashtray')
								(if (ego has: 10)
									(global122 setPri: 0)
									(global120 setPri: 0)
									(localproc_000c 32 109 83)
									(global122 setPri: 15)
									(global120 setPri: 14)
									(= local2 1)
								else
									(localproc_000c 32 108)
								)
							)
							((Said '/*') (localproc_000c 32 110))
						)
					)
					(
						(or
							(Said 'use/tape[<fingerprint]')
							(Said 'get,hoist/print,clue')
						)
						(if (ego has: 10)
							(cond 
								((== local2 2)
									(global123 setPri: 0)
									(localproc_000c 32 111 83)
									(global123 setPri: 15)
									(ego get: 22)
								)
								(local2 (localproc_000c 32 112))
								(else (localproc_000c 32 113))
							)
							(global122 setPri: 15)
							(global120 setPri: 14)
						else
							(localproc_000c 32 108)
						)
					)
					(
						(or
							(Said 'use,dial,pick[<up]/phone')
							(Said 'make/call')
						)
						(if (< gamePhase 8)
							(if (ego inRect: 150 117 178 135)
								(curRoom newRoom: 12)
							else
								(NotClose)
							)
						else
							(localproc_000c 32 114)
						)
					)
					((Said 'use,go/bathroom,(chamber<bath)')
						(if local1
							(localproc_000c 32 115)
						else
							(localproc_000c 32 116)
						)
					)
					((Said 'use/basin,oven,fridge') (localproc_000c 32 117))
					((Said 'clock,use,(turn<on)/television,set') (localproc_000c 32 118))
					((Said 'sat[<down]')
						(if (>= gamePhase 8)
							(localproc_000c 32 119)
						else
							(localproc_000c 32 120)
						)
					)
				)
			)
		)
	)
)

(instance keithScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= gamePhase 8)
					(User canControl: 0)
					(keith setMotion: MoveTo 255 140 self)
				)
			)
			(1
				(keith setMotion: MoveTo 240 135 self)
			)
			(2
				(if (not global127)
					(localproc_000c 32 0)
					(localproc_000c 32 1)
					(localproc_000c 32 121)
					(localproc_000c 32 122)
				)
				(= cycles 5)
				(User canControl: 1)
				(= global127 1)
			)
			(3
				(keith setMotion: MoveTo 210 109 self)
			)
			(4
				(keith setMotion: MoveTo 275 109)
			)
			(5 (keith stopUpd:))
			(6
				(localproc_000c 32 123)
				(ego setLoop: 3)
				(keith startUpd: setMotion: MoveTo 213 109 self)
			)
			(7
				(keith setMotion: MoveTo 215 135 self)
			)
			(8
				(ego setLoop: 2)
				(keith setMotion: MoveTo 238 135 self)
			)
			(9
				(keith setMotion: MoveTo 238 145 self)
			)
			(10
				(ego setLoop: -1)
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance lampBlock of Block
	(properties)
)

(instance bathroomScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 stopUpd: setPri: 8)
				(newProp setCycle: EndLoop)
				(= local1 1)
				(= seconds 2)
			)
			(1
				(if (< gamePhase 8)
					(localproc_000c 32 124 83)
				else
					(localproc_000c 32 125 83)
				)
				(= seconds 2)
			)
			(2 (newProp setCycle: BegLoop self))
			(3
				(newProp stopUpd:)
				(ego setPri: -1)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
