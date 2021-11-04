;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include system.sh)
(include game.sh)
(include keys.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm19 0
)

(local
	marshall
	revolver
	door1
	door2
	door3
	[local5 4]
	flood
	local10
	sonnyInStallNum
	local12
	local13
	local14
	local15
	marshallBlocked
	local17
	tolietLid
	tank
	local20
)
(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance rm19 of Room
	(properties
		picture 19
		style HWIPE
	)
	
	(method (init)
		(super init:)
		(= gunFireState 2)
		(= gunNotNeeded 1)
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 256)
		(Load VIEW 80)
		(Load PICTURE 35)
		(ego
			posn: 269 140
			view: (if gunDrawn 7 else 1)
			setStep: 3 2
			init:
			illegalBits: -24576
			setMotion: MoveTo 0 140
		)
		(self setScript: rm19Script)
		(self setLocales: 153)
	)
	
	(method (dispose)
		(stallScript dispose:)
		(floodScript dispose:)
		(marshallScript dispose:)
		(super dispose:)
	)
)

(instance rm19Script of Script
	(properties)
	
	(method (doit)
		(if (> local12 0)
			(-- local12)
		)
		(cond 
			((> (ego x?) 270)
				(curRoom newRoom: 16)
			)
			((<= (ego y?) 126)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1) (ego view: (+ (ego view?) 1)))
		)
		(if
			(and
				local13
				local15
				(not (ego inRect: 174 115 225 138))
			)
			(= local15 0)
			(LocPrint 19 0)
		)
		(if
			(and
				local13
				(ego inRect: 174 115 225 138)
			)
			(= local15 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(StatusLine enable:)
				(HandsOn)
				(User prevDir: 7)
				(if
					(and
						(== gamePhase 13)
						(not local10)
						(not (Btst fMarshallLeftBathroom))
					)
					((= marshall (Actor new:))
						view: 21
						posn: (if (Btst fMarshallAtSink) 192 else 138) (if (Btst fMarshallAtSink) 119 else 120)
						init:
						illegalBits: 0
						setCycle: Walk
						setScript: marshallScript
					)
				)
				((= flood (Prop new:))
					view: 80
					loop: 3
					cel: 0
					cycleSpeed: 4
					setPri: 7
					ignoreActors:
					posn: 136 137
					init:
					stopUpd:
					setScript: floodScript
				)
				((= door1 (Prop new:))
					view: 80
					loop: 0
					cel: 0
					posn: 92 143
					cycleSpeed: 2
					setPri: 10
					init:
					stopUpd:
				)
				((= door2 (Prop new:))
					view: 80
					loop: 1
					cel: 0
					cycleSpeed: 2
					posn: 109 133
					setPri: 9
					init:
					stopUpd:
				)
				((= door3 (Prop new:))
					view: 80
					loop: 2
					cel: 0
					cycleSpeed: 2
					posn: 115 122
					setPri: 8
					init:
					stopUpd:
				)
				((View new:)
					view: 80
					loop: 5
					cel: 0
					posn: 210 114
					setPri: 7
					init:
					addToPic:
				)
				(if local10
					(switch sonnyInStallNum
						(1
							(self changeState: 1)
						)
						(2
							(self changeState: 5)
						)
						(3
							(self changeState: 9)
						)
					)
				)
			)
			(1
				(HandsOff)
				(door1
					startUpd:
					setCycle: EndLoop self
				)
				(= sonnyInStallNum 1)
			)
			(2
				(ego ignoreActors: illegalBits: 0)
				(if local10
					(= local10 0)
					(= sonnyInStallNum 0)
					(ego setMotion: MoveTo 110 143 self)
				else
					(ego setMotion: MoveTo 64 143 self)
				)
			)
			(3
				(ego ignoreActors: 0 illegalBits: -24576)
				(door1 ignoreActors: setCycle: BegLoop self)
			)
			(4
				(HandsOn)
				(door1 stopUpd:)
			)
			(5
				(HandsOff)
				(door2 startUpd: setCycle: EndLoop self)
				(= sonnyInStallNum 2)
			)
			(6
				(ego ignoreActors: illegalBits: 0)
				(if local10
					(= sonnyInStallNum 0)
					(= local10 0)
					(ego setMotion: MoveTo 125 131 self)
				else
					(ego setMotion: MoveTo 86 131 self)
				)
			)
			(7
				(ego ignoreActors: 0 illegalBits: -24576)
				(door2 ignoreActors: setCycle: BegLoop self)
			)
			(8
				(HandsOn)
				(door2 stopUpd:)
			)
			(9
				(door3 startUpd: setCycle: EndLoop self)
				(HandsOff)
				(= sonnyInStallNum 3)
			)
			(10
				(ego ignoreActors: illegalBits: 0)
				(if local10
					(= sonnyInStallNum 0)
					(= local10 0)
					(ego setMotion: MoveTo 135 120 self)
				else
					(ego setMotion: MoveTo 108 120 self)
				)
			)
			(11
				(ego ignoreActors: 0 illegalBits: cWHITE) ;-24576
				(door3 ignoreActors: setCycle: BegLoop self)
			)
			(12
				(HandsOn)
				(door3 stopUpd:)
			)
			(13
				(switch sonnyInStallNum
					(2
						(if
						(and (InRoom 31) (>= gamePhase 6) (< gamePhase 8))
							(LocPrint 19 1)
							(floodScript changeState: 1)
							(= global128 1)
						else
							(LocPrint 19 2)
						)
					)
					(0 (LocPrint 19 3))
					(else  (LocPrint 19 2))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(keyDown
				(if
					(and
						sonnyInStallNum
						(or
							(== (event message?) KEY_F6)
							(== (event message?) KEY_F8)
							(== (event message?) KEY_F10)
						)
					)
					(event claimed: 1)
					(Print 19 4)
				)
			)
			(saidEvent
				(cond 
					(
						(or
							(Said 'open[/booth,stall,door]')
							(and
								(not sonnyInStallNum)
								(Said 'enter[/booth,stall]')
							)
							(and
								sonnyInStallNum
								(or
									(Said 'exit[/booth,stall]')
									(Said 'get<out')
								)
							)
						)
						(cond 
							(
								(and
									(not sonnyInStallNum)
									gunDrawn
								)
								(Print 19 5)
							)
							((ego inRect: 117 116 140 126)
								(self changeState: 9)
							)
							((ego inRect: 104 126 130 136)
								(self changeState: 5)
							)
							((ego inRect: 86 136 118 149)
								(self changeState: 1)
							)
							((== sonnyInStallNum 1)
								(= local10 1)
								(self changeState: 1)
							)
							((== sonnyInStallNum 2)
								(= local10 1)
								(self changeState: 5)
							)
							((== sonnyInStallNum 3)
								(= local10 1)
								(self changeState: 9)
							)
							(else
								(LocPrint 19 6)
							)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/dirt')
								(LocPrint 19 7)
							)
							((Said '/draw')
								(if sonnyInStallNum
									(LocPrint 19 8)
								else
									(LocPrint 19 9)
								)
							)
							((Said '/wall')
								(if sonnyInStallNum
									(LocPrint 19 10)
									(LocPrint 19 11)
								else
									(LocPrint 19 12)
								)
							)
							((Said '/water')
								(if sonnyInStallNum
									(LocPrint 19 13)
								else
									(LocPrint 19 14)
								)
							)
							((Said '/vent,fan')
								(LocPrint 19 15)
							)
							((Said '/drain')
								(if (== (floodScript state?) 2)
									(LocPrint 19 16)
								else
									(LocPrint 19 17)
								)
							)
							((Said '/dude,gentleman,marshal')
								(if (cast contains: marshall)
									(LocPrint 19 18)
								else
									(LocPrint 19 19)
								)
							)
							((Said '<below/basin')
								(LocPrint 19 20)
							)
							((Said '/cabinet')
								(LocPrint 19 21)
							)
							((Said '/outlet')
								(LocPrint 19 22)
							)
							((Said '/door')
								(LocPrint 19 23)
							)
							((Said '/basin')
								(if sonnyInStallNum
									(LocPrint 19 24)
								else
									(LocPrint 19 25)
								)
							)
							((Said '/bracket<soap')
								(LocPrint 19 26)
							)
							(
								(or
									(Said '/bracket,machine[<towel]')
									(Said '/bracket,machine<towel<newspaper')
								)
								(if sonnyInStallNum
									(LocPrint 19 24)
								else
									(LocPrint 19 27)
								)
							)
							((Said '/dryer')
								(if sonnyInStallNum
									(LocPrint 19 24)
								else
									(LocPrint 19 28)
								)
							)
							(
								(or
									(Said '<up')
									(Said '/ceiling,light')
								)
								(LocPrint 19 29)
							)
							(
								(or
									(Said '<down')
									(Said '/floor,tile')
								)
								(if sonnyInStallNum
									(LocPrint 19 30)
								else
									(LocPrint 19 31)
								)
							)
							((Said '/9mm')
								(if (ego has: 31)
									(LocPrint 19 32)
								else
									(event claimed: 0)
								)
							)
							((Said '/mirror')
								(cond 
									(sonnyInStallNum
										(LocPrint 19 24)
									)
									((< (ego y?) 135)
										(if (== (ego loop?) 0)
											(LocPrint 19 33)
										else
											(LocPrint 19 34)
										)
									)
									(else
										(LocPrint 19 35)
									)
								)
							)
							((Said '/booth,stall')
								(if sonnyInStallNum
									(LocPrint 19 10)
									(LocPrint 19 11)
								else
									(LocPrint 19 36)
								)
							)
							((Said '/crapper,crapper,tank')
								(if sonnyInStallNum
									(curRoom setScript: stallScript)
								else
									(LocPrint 19 37)
								)
							)
							((Said '[<at,around][/chamber,bathroom,bathroom]')
								(if sonnyInStallNum
									(LocPrint 19 10)
									(LocPrint 19 11)
									(curRoom setScript: stallScript)
								else
									(LocPrint 19 38)
								)
							)
						)
					)
					((Said 'open,unlock/cabinet')
						(LocPrint 19 39)
					)
					((Said 'smell/odor')
						(LocPrint 19 40)
					)
					(
						(or
							(Said 'move,remove,hoist,open/cover,lid,lid')
							(Said 'open/tank,crapper')
							(Said 'frisk/tank')
						)
						(if sonnyInStallNum
							(= [local5 sonnyInStallNum] 1)
							(curRoom setScript: stallScript)
						else
							(LocPrint 19 35)
						)
					)
					((Said 'taste,drink,drink[/water]')
						(LocPrint 19 41)
					)
					(
						(or
							(Said 'read/graffiti,wall')
							(Said 'look/graffiti')
						)
						(if sonnyInStallNum
							(switch (Random 0 5)
								(0
									(LocPrint 19 42)
								)
								(1
									(LocPrint 19 43)
								)
								(2
									(LocPrint 19 44)
								)
								(3
									(LocPrint 19 45)
								)
								(4
									(LocPrint 19 46)
								)
								(5
									(LocPrint 19 47)
								)
							)
						else
							(LocPrint 19 48)
						)
					)
					((Said 'write')
						(LocPrint 19 49)
					)
					(
						(or
							(Said 'crap')
							(Said 'get/crap')
						)
						(if sonnyInStallNum
							(switch (Random 0 2)
								(0
									(LocPrint 19 50)
								)
								(1
									(LocPrint 19 51)
								)
								(2
									(LocPrint 19 52)
								)
							)
						else
							(LocPrint 19 53)
						)
					)
					(
						(or
							(Said 'leak')
							(Said 'get/leak')
						)
						(if sonnyInStallNum
							(switch (Random 0 3)
								(0
									(LocPrint 19 54)
								)
								(1
									(LocPrint 19 55)
								)
								(2
									(LocPrint 19 56)
								)
								(3
									(LocPrint 19 57)
								)
							)
						else
							(LocPrint 19 53)
						)
					)
					((Said 'flush[/crapper]')
						(self changeState: 13)
					)
					(
						(or
							(Said 'wipe/ass')
							(Said 'use/newspaper[<crapper]')
						)
						(if sonnyInStallNum
							(LocPrint 19 58)
						else
							(LocPrint 19 59)
						)
					)
					((Said 'sat')
						(if sonnyInStallNum
							(LocPrint 19 60)
						else
							(LocPrint 19 61)
						)
					)
					((Said 'frisk,check[<out]/stall,booth')
						(if sonnyInStallNum
							(LocPrint 19 62)
						else
							(LocPrint 19 63)
						)
					)
					((Said 'frisk,check[<out]/crapper,tank')
						(if sonnyInStallNum
							(curRoom setScript: stallScript)
						else
							(LocPrint 19 63)
						)
					)
					((Said 'frisk,check[<out]')
						(LocPrint 19 64)
					)
					((Said 'chat/dude,gentleman,marshal')
						(if (cast contains: marshall)
							(LocPrint 19 65)
						else
							(LocPrint 19 66)
						)
					)
					((Said 'display/mugshot,shot,painting')
						(if (cast contains: marshall)
							(LocPrint 19 67)
						else
							(LocPrint 19 68)
						)
					)
					((Said 'get,use/towel,bracket')
						(if (ego inRect: 133 116 178 122)
							(LocPrint 19 69)
						else
							(LocPrint 19 70)
						)
					)
					((Said 'press/button')
						(if (ego inRect: 155 116 182 122)
							(if (> local12 0)
								(LocPrint 19 71)
							else
								(LocPrint 19 72)
								(= local12 80)
							)
						else
							(LocPrint 19 19)
						)
					)
					((Said 'use,go/crapper,bathroom,bathroom,(chamber<rest,bath)')
						(if sonnyInStallNum
							(switch (Random 0 3)
								(0
									(LocPrint 19 54)
								)
								(1
									(LocPrint 19 55)
								)
								(2
									(LocPrint 19 56)
								)
								(3
									(LocPrint 19 57)
								)
							)
						else
							(LocPrint 19 73)
						)
					)
					(
						(or
							(Said 'activate,begin,use>')
							(Said 'turn<on>')
						)
						(cond 
							((Said '/dryer')
								(if (ego inRect: 155 116 182 122)
									(if (> local12 0)
										(LocPrint 19 74)
									else
										(LocPrint 19 72)
										(= local12 80)
									)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/basin,faucet,water')
								(if (ego inRect: 174 115 225 138)
									(if local13
										(LocPrint 19 74)
									else
										(LocPrint 19 75)
										(= local13 1)
									)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/fan')
								(switch (Random 0 2)
									(0
										(LocPrint 19 3)
									)
									(1
										(LocPrint 19 76)
									)
									(2
										(LocPrint 19 74)
									)
								)
							)
						)
					)
					(
						(or
							(Said 'deactivate,cease>')
							(Said 'turn,close<off>')
						)
						(cond 
							((Said '/dryer')
								(if (ego inRect: 155 116 182 122)
									(if (> local12 0)
										(LocPrint 19 77)
										(= local12 80)
									else
										(LocPrint 19 78)
									)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/faucet,basin,water')
								(if (ego inRect: 174 115 225 138)
									(if local13
										(LocPrint 19 79)
										(= local13 0)
									else
										(LocPrint 19 78)
									)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/fan')
								(if (== (Random 0 2) 1)
									(LocPrint 19 3)
								else
									(LocPrint 19 76)
								)
							)
						)
					)
					((Said 'dry>')
						(cond 
							((Said '/9mm,revolver')
								(if (ego inRect: 133 116 178 122)
									(if (ego has: iJailerRevolver)
										(cond 
											((ego inRect: 155 116 182 122)
												(if (> local12 0)
													(if local17
														(LocPrint 19 80)
													else
														(SolvePuzzle 2 107)
														(= local17 1)
														(LocPrint 19 81)
													)
												else
													(LocPrint 19 82)
												)
											)
											(local17 (LocPrint 19 80))
											(else
												(= local17 1)
												(LocPrint 19 83)
												(LocPrint 19 84)
											)
										)
									else
										(LocPrint 19 85)
									)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/hand')
								(cond 
									((ego inRect: 133 116 178 122)
										(if
											(or
												(ego has: iJailerRevolver)
												local14
											)
											(= local14 0)
											(if local12
												(LocPrint 19 86)
											else
												(LocPrint 19 87)
											)
										else
											(LocPrint 19 88)
										)
									)
									(
										(or
											(ego has: 31) local14)
											(LocPrint 19 87)
										)
									(else
										(LocPrint 19 88)
									)
								)
							)
						)
					)
					((Said 'bath>')
						(cond 
							((Said '/hand')
								(if (ego inRect: 174 115 225 138)
									(= local14 1)
									(LocPrint 19 89)
								else
									(LocPrint 19 3)
								)
							)
							((Said '/9mm,revolver') (LocPrint 19 90))
						)
					)
				)
			)
		)
	)
)

(instance stallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom drawPic: 35)
				(cast eachElementDo: #dispose)
				(if
					(and
						(InRoom iJailerRevolver)
						(== sonnyInStallNum 2)
						(>= gamePhase 6)
						(< gamePhase 8)
					)
					((= revolver (Actor new:))
						view: 256
						setLoop: 2
						setCel: 0
						setPri: 6
						posn: 167 133
						init:
						illegalBits: 0
						ignoreActors:
						stopUpd:
					)
				)
				((= tank (View new:))
					view: 256
					loop: 0
					cel: 0
					posn: 155 137
					setPri: 14
					init:
					stopUpd:
				)
				((= tolietLid (View new:))
					view: 256
					loop: 1
					cel: 0
					posn: 154 76
					setPri: 15
					init:
					stopUpd:
				)
				(if [local5 sonnyInStallNum]
					(tolietLid posn: -100 0)
				)
				((= flood (Prop new:))
					view: 256
					loop: 3
					cel: 0
					posn: 221 92
					setPri: 14
					init:
					ignoreActors:
					setScript: floodScript
				)
				(User canInput: 1)
			)
			(2
				(HandsOff)
				(revolver setMotion: MoveTo 167 60 self)
			)
			(3
				(SolvePuzzle 4)
				(tank posn: 155 137)
				(ego get: 31)
				(revolver dispose:)
				(flood dispose:)
				(LocPrint 19 91 83)
				(HandsOn)
				(self cue:)
			)
			(4
				(if (!= (cSound state?) 3)
					(cSound number: 29 loop: -1 play:)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (event message?) KEY_F6)
						(== (event message?) KEY_F8)
						(== (event message?) KEY_F10) ;14848
					)
					(CantDo)
				)
			)
			(saidEvent
				(cond 
					(
						(or
							(Said 'move,remove,hoist,open/cover,lid,lid')
							(Said 'open[/tank,crapper]')
							(Said 'frisk/tank')
						)
						(if (== [local5 sonnyInStallNum] 0)
							(tolietLid posn: -100 0)
							(= [local5 sonnyInStallNum] 1)
						else
							(LocPrint 19 92)
						)
					)
					((Said 'replace,close,deposit>')
						(cond 
							((Said '/lid,cover,lid')
								(if (== [local5 sonnyInStallNum] 1)
									(= [local5 sonnyInStallNum] 0)
									(tolietLid posn: 154 75)
									(tank posn: 155 137)
								else
									(LocPrint 19 93)
								)
							)
							((Said '/9mm')
								(LocPrint 19 94)
							)
							((Said '/hand/water,tank')
								(LocPrint 19 95)
							)
						)
					)
					((Said 'hoist,get,remove/9mm,revolver')
						(cond 
							((ego has: iJailerRevolver)
								(LocPrint 19 96)
							)
							(
								(and
									(== sonnyInStallNum 2)
									(cast contains: revolver)
									(== [local5 sonnyInStallNum] 1)
								)
								(if (== (tank y?) 0)
									(LocPrint 19 97 25 3)
									(Bset fGotToiletGun)
									(= global128 0)
									(stallScript changeState: 2)
								else
									(LocPrint 19 98)
								)
							)
							(else
								(LocPrint 19 99)
							)
						)
					)
					(
						(or
							(Said 'exit[/booth,stall,crapper]')
							(Said 'get<out')
							(Said 'open/door')
						)
						(curRoom drawPic: (curRoom picture?))
						(cast eachElementDo: #dispose)
						(ego init: loop: 0)
						(= local10 1)
						(HandsOn)
						(curRoom setScript: rm19Script)
						(cond 
							((== sonnyInStallNum 1)
								(= local10 1)
								(rm19Script changeState: 1)
							)
							((== sonnyInStallNum 2)
								(= local10 1)
								(rm19Script changeState: 5)
							)
							((== sonnyInStallNum 3)
								(= local10 1)
								(rm19Script changeState: 9)
							)
						)
					)
					(
						(or
							(Said 'flush[/crapper]')
							(Said 'use,press/handle')
						)
						(rm19Script changeState: 13)
					)
					(
						(or
							(Said 'use/newspaper[<crapper]')
							(Said 'wipe/ass')
						)
						(LocPrint 19 58)
					)
					(
						(or
							(Said 'read/graffiti,wall')
							(Said 'look/graffiti')
						)
						(switch (Random 0 5)
							(0
								(LocPrint 19 42)
							)
							(1
								(LocPrint 19 43)
							)
							(2
								(LocPrint 19 44)
							)
							(3
								(LocPrint 19 45)
							)
							(4
								(LocPrint 19 46)
							)
							(5
								(LocPrint 19 47)
							)
						)
					)
					((Said 'write')
						(LocPrint 19 49)
					)
					(
						(or
							(Said 'crap')
							(Said 'get/crap')
						)
						(switch (Random 0 2)
							(0
								(LocPrint 19 50)
							)
							(1
								(LocPrint 19 51)
							)
							(2
								(LocPrint 19 52)
							)
						)
					)
					(
						(or
							(Said 'leak')
							(Said 'get/leak')
							(Said 'use/crapper')
						)
						(switch (Random 0 3)
							(0
								(LocPrint 19 54)
							)
							(1
								(LocPrint 19 55)
							)
							(2
								(LocPrint 19 56)
							)
							(3
								(LocPrint 19 57)
							)
						)
					)
					((Said 'taste,drink,drink[/water]')
						(LocPrint 19 100)
					)
					((Said 'sat')
						(LocPrint 19 60)
					)
					((Said 'frisk/crapper,stall,booth')
						(LocPrint 19 62)
					)
					((Said 'look>')
						(cond 
							(
								(or
									(Said '[<in,in]/crapper,tank,water')
									(Said '<in,in[/crapper,tank,water]')
								)
								(if [local5 sonnyInStallNum]
									(tank posn: -100 0)
									(if
										(and
											(not (ego has: iJailerRevolver))
											(cast contains: revolver)
											(== sonnyInStallNum 2)
										)
										(LocPrint 19 101 83)
									else
										(LocPrint 19 102 83)
									)
								else
									(LocPrint 19 103)
								)
								(if
									(and
										global128
										(== sonnyInStallNum 2)
										(not (cast contains: revolver))
									)
									(LocPrint 19 104)
								)
							)
							((Said '/handle')
								(LocPrint 19 105)
							)
							((Said '[<at,down][/floor,tile]')
								(LocPrint 19 30)
							)
							((Said '/9mm')
								(cond 
									((ego has: 31)
										(LocPrint 19 106)
									)
									(
										(and
											(== (tank y?) 0)
											(cast contains: iJailerRevolver)
										)
										(LocPrint 19 101)
									)
									(else
										(LocPrint 19 107)
									)
								)
							)
							((Said '/basin')
								(LocPrint 19 24)
							)
							((Said '/mirror')
								(LocPrint 19 24)
							)
							((Said '[<at,around][/booth,stall,wall]')
								(LocPrint 19 10)
								(LocPrint 19 11)
							)
							(
								(or
									(Said '/bracket,machine[<towel]')
									(Said '/bracket,machine<towel<newspaper')
								)
								(LocPrint 19 24)
							)
							((Said '/dryer')
								(LocPrint 19 24)
							)
							((Said '/newspaper[<crapper]')
								(LocPrint 19 108)
							)
						)
					)
				)
			)
		)
	)
)

(instance floodScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if global128
					(self changeState: 2)
				)
			)
			(1
				(flood setCycle: EndLoop self)
			)
			(2
				(flood loop: 4 setCycle: Forward)
			)
		)
	)
)

(instance marshallScript of Script
	(properties)
	
	(method (init)
		(super init:)
		(= gunFireState 3)
	)
	
	(method (doit)
		(if
			(and
				(<= (marshall distanceTo: ego) 10)
				(not marshallBlocked)
			)
			(= marshallBlocked 1)
			(LocPrint 19 109)
			(ego setMotion: MoveTo (- (ego x?) 25) (ego y?))
		)
		(if
			(and
				(> (marshall distanceTo: ego) 10)
				marshallBlocked
			)
			(= marshallBlocked 0)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fMarshallAtSink)
				(marshall
					startUpd:
					setMotion: MoveTo 192 119 self
				)
			)
			(1
				(= cycles 60)
			)
			(2
				(marshall
					setLoop: 2
					setMotion: MoveTo 200 139 self
				)
			)
			(3
				(marshall
					setLoop: 0
					setMotion: MoveTo 286 140 self
				)
			)
			(4
				(Bset fMarshallLeftBathroom)
				(marshall dispose:)
			)
		)
	)
)
