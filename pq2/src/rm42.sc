;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include sci.sh)
(use Main)
(use jet)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm42 0
)

(local
	[str 100]
	bombArmed
	local101
	local102
	local103
	local104
	local105
	local106
	newProp_3
	newProp_2
	newProp
	local110
	theBombTimerSeconds
	newProp_8
	newProp_4
	newProp_5
	newProp_6
	newProp_7
	newView_2
	newProp_9
	newProp_10
	newProp_11
	local121
	newView
	local123
	blueWireCut
	whiteWireCut
	yellowWireCut
	purpleWireCut
)
(procedure (localproc_02f6)
	((View new:)
		view: 84
		loop: 0
		cel: 3
		posn: 263 75
		ignoreActors:
		setPri: 1
		addToPic:
	)
	((View new:)
		view: 84
		loop: 1
		setCel: 255
		posn: 268 60
		ignoreActors:
		setPri: 2
		addToPic:
	)
	((= newProp (Prop new:))
		view: 82
		posn: 271 55
		loop: 8
		cel: 0
		setPri: 0
		ignoreActors:
		cycleSpeed: 2
		init:
		stopUpd:
	)
	((= newProp_2 (Prop new:))
		view: 82
		posn: 51 191
		loop: 2
		cel: 0
		ignoreActors:
		setPri: 15
		init:
		stopUpd:
	)
	((= newProp_3 (Prop new:))
		view: 82
		posn: 65 158
		loop: 5
		setCel: local106
		setPri: 10
		ignoreActors:
		init:
		stopUpd:
	)
	(if
	(and (== 0 (ego x?)) (== (ego x?) (ego y?)))
		(ego posn: 230 61)
	)
	(ego illegalBits: -32768 init:)
	(GoToBathroom)
)

(procedure (localproc_0e92 &tmp bombTimerSeconds temp1 temp2 temp3)
	(if
	(< (= bombTimerSeconds (bombTimer seconds?)) 0)
		(= bombTimerSeconds 0)
	)
	(if (!= theBombTimerSeconds bombTimerSeconds)
		(= theBombTimerSeconds bombTimerSeconds)
		(= temp3 (mod bombTimerSeconds 10))
		(= temp2
			(mod (= bombTimerSeconds (/ bombTimerSeconds 10)) 10)
		)
		(if
			(not
				(= temp1
					(mod (= bombTimerSeconds (/ bombTimerSeconds 10)) 10)
				)
			)
			(= temp1 10)
			(if (not temp2) (= temp2 10))
		)
		(newProp_9 posn: 244 36 cel: temp1)
		(newProp_10 posn: 254 36 cel: temp2)
		(newProp_11 posn: 264 36 cel: temp3)
	)
)

(procedure (localproc_0f1f)
	(Print &rest #at -1 150 #font smallFont)
)

(instance explosionSound of Sound
	(properties
		number 30
		priority 10
	)
)

(instance bombTimer of Timer
	(properties)
)

(instance Bathroom of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
			(or (event claimed?) (!= (event type?) evSAID)) (return))
			((not local101)
				(if (Said '/bracket,basin,garbage,crapper')
					(NotClose)
				else
					(return)
				)
			)
			((Said '/garbage,can[<garbage]>')
				(cond 
					((Said 'look')
						(if (not bombArmed)
							(AirplanePrint 42 0)
						else
							(AirplanePrint 42 1)
						)
					)
					((Said 'frisk')
						(if (not bombArmed)
							(AirplanePrint 42 2)
						else
							(AirplanePrint 42 1)
						)
					)
					((Said 'get') (AirplanePrint 42 3))
					((Said 'read') (AirplanePrint 42 4))
					(else (event claimed: 0))
				)
			)
			((Said 'flush,use/crapper') (AirplanePrint 42 5))
			((Said 'bath/hand') (AirplanePrint 42 6))
			((Said 'turn/faucet,water') (AirplanePrint 42 7))
			((Said 'open/bracket,lid')
				(cond 
					((not local101) (AirplanePrint 42 8))
					((== (curRoom script?) TowelDispenser) (event claimed: 0))
					(else (= local103 1) (curRoom setScript: TowelDispenser))
				)
			)
			((Said 'open/door') (Print 42 9))
			((Said '(look,frisk)>')
				(cond 
					((Said '/wall') (AirplanePrint 42 10))
					((Said '/mirror')
						(if (not bombArmed)
							(AirplanePrint 42 11)
						else
							(AirplanePrint 42 12)
						)
					)
					((Said '/basin')
						(if (not bombArmed)
							(AirplanePrint 42 13)
							(AirplanePrint 42 14)
						else
							(AirplanePrint 42 1)
						)
					)
					((Said '/crapper')
						(if (not bombArmed)
							(AirplanePrint 42 15)
						else
							(AirplanePrint 42 1)
						)
					)
					((Said '/bracket[<towel]')
						(if (!= (curRoom script?) 0)
							(AirplanePrint 42 16)
						else
							(= local103 local121)
							(curRoom setScript: TowelDispenser)
						)
					)
					((Said '/pane') (AirplanePrint 42 17))
					((Said '[<at,around][/!*,chamber,bathroom]') (AirplanePrint 42 18))
				)
			)
		)
	)
)

(instance rm42 of Room
	(properties
		picture 40
		style $0000
	)
	
	(method (init)
		(Load rsVIEW 0)
		(Load rsVIEW 82)
		(Load rsVIEW 84)
		(Load rsPIC 300)
		(super init:)
		(self setLocales: 154)
		(self setFeatures: Bathroom)
		(= local106 3)
		(localproc_02f6)
		(= wearingSeatbelt 0)
		(= sittingInPlane 0)
		(= local123 1)
		(= blueWireCut 0)
		(= whiteWireCut 0)
		(= yellowWireCut 0)
		(= purpleWireCut 0)
		(HandsOn)
		(bombTimer setReal: doBlowUp 120)
	)
	
	(method (doit)
		(if (not (self script?))
			(cond 
				(
				(and (& (ego onControl:) $2000) (not local101)) (curRoom setScript: brDoor) (brDoor changeState: 10))
				((and (& (ego onControl:) $0400) local101) (curRoom setScript: brDoor) (brDoor changeState: 1))
				((and bombArmed (not local101)) (curRoom newRoom: 43))
			)
		)
		(if (& (ego onControl:) $4000)
			(if (not local105) (= local105 1) (Print 42 19))
		else
			(= local105 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(bombTimer dispose: delete:)
		(Bathroom dispose:)
		(egoSearch dispose:)
		(brDoor dispose:)
		(TowelDispenser dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (!= (event type?) evSAID) (return))
		(cond 
			((ego inRect: 255 50 300 75) (= local102 1))
			((& (ego onControl:) $1800) (= local102 2))
			((and (>= (ego y?) 57) (<= (ego y?) 143)) (= local102 3))
			(else (= local102 0))
		)
		(cond 
			(
				(or
					(Said '/*<(hijacker,dude)<unmasked')
					(Said '//(hijacker,dude)<unmasked')
					(Said '/<unmasked')
				)
				(if (!= local102 1) (NotClose) (return))
				(= local104 2)
				(event claimed: 0)
			)
			(
				(or
					(Said '/*<(hijacker,dude)<masked')
					(Said '//(hijacker,dude)<masked')
					(Said '/<masked')
				)
				(if (!= local102 1) (NotClose) (return))
				(= local104 1)
				(event claimed: 0)
			)
			((Said '/body,hijacker')
				(if (and (!= local102 1) (!= local102 2))
					(NotClose)
					(return)
				)
				(event claimed: 0)
			)
		)
		(cond 
			((Said 'sat[/*]')
				(if (not bombArmed)
					(AirplanePrint 42 20)
				else
					(AirplanePrint 42 5)
				)
			)
			((Said '/9mm>')
				(cond 
					((!= local102 1) (event claimed: 1) (NotClose))
					((Said 'get') (AirplanePrint 42 21))
					((Said 'look') (AirplanePrint 42 22))
				)
			)
			((Said '/(bench,baggage)')
				(if (== local102 3)
					(AirplanePrint 42 23)
				else
					(NotClose)
				)
			)
			((Said '/compartment,bin,cabinet')
				(if (== local102 3)
					(AirplanePrint 42 24)
				else
					(NotClose)
				)
			)
			(
			(and (== local102 3) (Said '/passenger,dude,broad>'))
				(cond 
					((Said 'look') (AirplanePrint 42 25))
					((Said 'chat')
						(switch (Random 0 2)
							(0 (AirplanePrint 42 26))
							(1 (AirplanePrint 42 27))
							(else  (AirplanePrint 42 28))
						)
					)
					(else (event claimed: 1) (AirplanePrint 42 29))
				)
			)
			((Said '/passenger') (NotClose))
			(
				(or
					(Said 'look/(hijacker,body,dude)')
					(Said 'frisk/cloth')
				)
				(cond 
					((and (!= local102 2) (!= local102 1)) (NotClose))
					((== local104 2) (AirplanePrint 42 30))
					(else (AirplanePrint 42 31))
				)
			)
			((Said 'open/door')
				(cond 
					((& (ego onControl:) $4000) (AirplanePrint 42 19))
					((or (== local102 2) (== local102 1)) (AirplanePrint 42 32))
					(else (event claimed: 0))
				)
			)
			((Said 'check/(hijacker,body,dude)')
				(if (!= local102 1)
					(NotClose)
				else
					(AirplanePrint 42 33)
				)
			)
			((Said 'frisk[<around][/!*,area]') (AirplanePrint 42 34))
			((Said '(frisk,look)>')
				(cond 
					(
						(or
							(Said '/unmasked')
							(Said '/(body,hijacker,dude)<unmasked')
						)
						(= local104 2)
						(AirplanePrint 42 35)
					)
					(
						(or
							(Said '/masked')
							(Said '/(body,hijacker,dude)<masked')
						)
						(= local104 1)
						(AirplanePrint 42 36)
					)
					((Said '/(dude,body,hijacker)') (AirplanePrint 42 37))
					((Said '/pocket,coat')
						(cond 
							((!= local102 1) (NotClose))
							((== local104 2)
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 38)
							)
							((ego has: 9)
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 39)
							)
							(else
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 40)
								(ego get: 9)
								(SolvePuzzle 3)
							)
						)
					)
					((Said '/shirt')
						(if (!= local102 1)
							(NotClose)
						else
							(AssignObjectToScript ego egoSearch)
							(Print
								(Format
									@str
									42
									41
									(if (== local104 1) {masked} else {unmasked})
								)
							)
						)
					)
					((Said '/jeans')
						(cond 
							((!= local102 1) (NotClose))
							((== local104 2)
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 42)
								(AirplanePrint 42 43)
								(AirplanePrint 42 44)
							)
							(else (AssignObjectToScript ego egoSearch) (Print 42 45))
						)
					)
					((Said '/turban')
						(cond 
							((!= local102 1) (NotClose))
							((ego has: 33)
								(= local104 2)
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 46)
							)
							(else
								(= local104 2)
								(AssignObjectToScript ego egoSearch)
								(AirplanePrint 42 47)
								(ego get: 33)
								(SolvePuzzle 3)
							)
						)
					)
					((Said '/mask')
						(if (!= local102 1)
							(NotClose)
						else
							(= local104 1)
							(AirplanePrint 42 48)
						)
					)
					((Said '/bomb') (AirplanePrint 42 49))
				)
			)
			((Said '/unmasked')
				(if (= local102 1)
					(= local104 2)
					(AirplanePrint 42 50)
				else
					(Print 42 51)
				)
			)
			((Said '/masked')
				(if (= local102 1)
					(= local104 1)
					(AirplanePrint 42 52)
				else
					(Print 42 51)
				)
			)
		)
	)
)

(instance egoSearch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((and (== local104 2) (< (ego x?) 276)) (ego illegalBits: 0 setMotion: MoveTo 276 61 self))
					((and (== local104 1) (> (ego x?) 282)) (ego illegalBits: 0 setMotion: MoveTo 282 65 self))
					(else (self cue:))
				)
			)
			(1
				(ego
					view: 84
					setCel: 0
					cycleSpeed: 4
					setCycle: CycleTo 7 1 self
					setLoop:
						(cond 
							((== local104 2) 6)
							((>= (ego y?) 59) 7)
							((<= (ego x?) 270) 6)
							(else 5)
						)
				)
			)
			(2
				(ego setCel: 4 setCycle: CycleTo 7 1 self)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(HandsOn)
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 0
					illegalBits: -32768
				)
				(client setScript: 0)
			)
		)
	)
)

(instance brDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego setMotion: 0)
				(newProp_3 setCycle: EndLoop self)
			)
			(2
				(ego setPri: -1 setMotion: MoveTo 69 155 self)
			)
			(3
				(newProp_3 stopUpd:)
				(newProp_2 posn: 51 191)
				(RedrawCast)
				(newProp_2 stopUpd:)
				(ego setMotion: MoveTo 82 155 self)
			)
			(4
				(HandsOn)
				(ego loop: 0 setMotion: 0)
				(= local101 0)
				(curRoom setScript: 0)
			)
			(10
				(HandsOff)
				(newProp_2 posn: 100 1000)
				(newProp_3 startUpd:)
				(ego
					ignoreActors:
					posn: 69 155
					illegalBits: 0
					setMotion: MoveTo 55 162 self
				)
			)
			(11
				(newProp_3 setCycle: BegLoop self)
			)
			(12
				(HandsOn)
				(newProp_3 stopUpd:)
				(ego setPri: 13 ignoreActors: 0 illegalBits: -32768)
				(= local101 1)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance TowelDispenser of Script
	(properties)
	
	(method (doit)
		(if (== local110 1)
			(= local110 0)
			(HandsOff)
			(cSound stop:)
			(curRoom setScript: doBoomScript)
		)
		(if (== local123 6)
			(localproc_0f1f 42 53)
			(bombTimer seconds: -1)
			(cSound stop:)
			(newProp_9 posn: 244 36 cel: 10)
			(newProp_10 posn: 254 36 cel: 10)
			(newProp_11 posn: 264 36 cel: 10)
			(= local123 7)
			(= bombArmed 1)
		)
		(if (and local121 (>= (bombTimer seconds?) 0))
			(localproc_0e92)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose)
				(curRoom picture: 24)
				(curRoom drawPic: (curRoom picture?) style: 6)
				(HandsOff)
				(User canInput: 1)
				((= newProp_4 (Prop new:))
					view: 250
					loop: 3
					cel: blueWireCut
					posn: 128 91
					setPri: 4
					init:
				)
				((= newProp_5 (Prop new:))
					view: 250
					loop: 4
					cel: whiteWireCut
					posn: 120 96
					setPri: 4
					init:
				)
				((= newProp_6 (Prop new:))
					view: 250
					loop: 5
					cel: yellowWireCut
					posn: 119 92
					setPri: 4
					init:
				)
				((= newProp_7 (Prop new:))
					view: 250
					loop: 6
					cel: purpleWireCut
					posn: 120 88
					setPri: 4
					init:
				)
				((= newView (View new:))
					view: 250
					loop: 2
					cel: 0
					posn: 166 97
					setPri: 2
					init:
					stopUpd:
				)
				((= newProp_8 (Prop new:))
					view: 250
					loop: 0
					cel: 0
					posn: 154 103
					setPri: 15
					init:
					stopUpd:
				)
				((= newProp_9 (Prop new:))
					view: 250
					loop: 8
					cel: 0
					posn: 240 1000
					setPri: 15
					init:
				)
				((= newProp_10 (Prop new:))
					view: 250
					loop: 8
					cel: 0
					posn: 240 1000
					setPri: 15
					init:
				)
				((= newProp_11 (Prop new:))
					view: 250
					loop: 8
					cel: 0
					posn: 240 1000
					setPri: 15
					init:
				)
				((= newView_2 (View new:))
					view: 250
					loop: 7
					cel: 0
					posn: 235 1000
					setPri: 15
					init:
				)
				(if local103 (self cue:))
			)
			(1
				(newProp_8 loop: 1 posn: 155 103)
				(newView_2 posn: 235 40)
				(localproc_0e92)
				(SolvePuzzle 2 99)
				(= local121 1)
			)
			(10
				(newProp_8 setLoop: 0 posn: 154 103)
				(= seconds 2)
			)
			(11
				(= local121 0)
				(cast eachElementDo: #dispose)
				(= local106 0)
				(localproc_02f6)
				(curRoom picture: 40)
				(curRoom drawPic: (curRoom picture?) style: 0)
				(GoToBathroom)
				(newProp_2 posn: 50 1000)
				(ego posn: 46 165)
				(newProp_3 stopUpd:)
				(newProp stopUpd:)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '(get,move,remove)/bomb')
						(cond 
							((not local121) (CantDo))
							((> local123 5) (localproc_0f1f 42 54))
							(else
								(localproc_0f1f 42 55)
								(localproc_0f1f 42 56)
								(= local110 1)
							)
						)
					)
					((Said 'disarm')
						(cond 
							((not local121) (CantDo))
							((> local123 5) (localproc_0f1f 42 57))
							(else (localproc_0f1f 42 58))
						)
					)
					((Said 'look>')
						(cond 
							((Said '/wires')
								(if (not local121)
									(CantDo)
								else
									(localproc_0f1f 42 59)
								)
							)
							((Said '/cable>')
								(cond 
									((not local121) (event claimed: 1) (CantDo))
									((Said '/!*') (localproc_0f1f 42 60))
									((Said '/(cable<blue)')
										(localproc_0f1f
											(Format
												@str
												42
												61
												(if (== blueWireCut 1) {cut} else {connected})
											)
										)
									)
									((Said '/(cable<white)')
										(localproc_0f1f
											(Format
												@str
												42
												62
												(if (== whiteWireCut 1) {cut} else {connected})
											)
										)
									)
									((Said '/(cable<yellow)')
										(localproc_0f1f
											(Format
												@str
												42
												63
												(if (== yellowWireCut 1) {cut} else {connected})
											)
										)
									)
									((Said '/(cable<purple)')
										(localproc_0f1f
											(Format
												@str
												42
												64
												(if (== purpleWireCut 1) {cut} else {connected})
											)
										)
									)
									(else (event claimed: 1) (localproc_0f1f 42 65))
								)
							)
							((Said '/timer,(device[<timing])')
								(cond 
									((not local121) (localproc_0f1f 42 66))
									((> local123 5) (localproc_0f1f 42 67))
									(else (localproc_0f1f 42 68))
								)
							)
							((Said '/bomb')
								(if local121
									(localproc_0f1f 42 69)
								else
									(localproc_0f1f 42 70)
								)
							)
							((Said '/bracket')
								(if local121
									(localproc_0f1f 42 71)
								else
									(localproc_0f1f 42 72)
								)
							)
							((Said '/dynamite') (localproc_0f1f 42 73))
							((Said '[<at,around][/(!*,chamber,bathroom)]') (self changeState: 10))
						)
					)
					((or (Said 'exit,exit') (Said 'look/chamber')) (self changeState: 10))
					((Said 'open[/bracket,lid]')
						(if (not local121)
							(self changeState: 1)
						else
							(localproc_0f1f 42 74)
						)
					)
					((Said 'close[/bracket,lid]')
						(if local121
							(= local121 0)
							(self changeState: 10)
						else
							(localproc_0f1f 42 75)
						)
					)
					((or (Said 'get/towel') (Said 'use/bracket')) (localproc_0f1f 42 76))
					((Said 'pull,bite/cable,wires') (localproc_0f1f 42 77))
					((Said '(cut,clip,disconnect)/>')
						(cond 
							((not (ego has: 9)) (localproc_0f1f 42 78) (event claimed: 1))
							((> local123 5) (localproc_0f1f 42 79) (event claimed: 1))
							((not local121) (localproc_0f1f 42 80) (event claimed: 1))
							((or (Said '<blue') (Said '/cable<blue'))
								(if (== blueWireCut 1)
									(localproc_0f1f 42 81)
								else
									(localproc_0f1f 42 82)
									(newProp_4 cel: 1)
									(= blueWireCut 1)
									(switch local123
										(2
											(SolvePuzzle 3)
											(if (== purpleWireCut 1) (++ local123))
										)
										(else  (= local110 1))
									)
								)
							)
							((or (Said '<white') (Said '/cable<white'))
								(if (== whiteWireCut 1)
									(localproc_0f1f 42 81)
								else
									(localproc_0f1f 42 82)
									(newProp_5 cel: 1)
									(= whiteWireCut 1)
									(if (!= local123 4)
										(= local110 1)
									else
										(++ local123)
										(SolvePuzzle 3)
									)
								)
							)
							((or (Said '<yellow') (Said '/cable<yellow'))
								(if (== yellowWireCut 1)
									(localproc_0f1f 42 81)
								else
									(localproc_0f1f 42 82)
									(newProp_6 cel: 1)
									(= yellowWireCut 1)
									(switch local123
										(1
											(++ local123)
											(SolvePuzzle 3)
										)
										(5
											(++ local123)
											(SolvePuzzle 3)
										)
										(else  (= local110 1))
									)
								)
							)
							((or (Said '<purple') (Said '/cable<purple'))
								(if (== purpleWireCut 1)
									(localproc_0f1f 42 81)
								else
									(localproc_0f1f 42 82)
									(newProp_7 cel: 1)
									(= purpleWireCut 1)
									(if (!= local123 2)
										(= local110 1)
									else
										(SolvePuzzle 3)
										(if (== blueWireCut 1) (++ local123))
									)
								)
							)
							(else (event claimed: 1) (localproc_0f1f 42 65))
						)
					)
					((Said 'attach,(deposit<together)/>')
						(cond 
							((not local121) (event claimed: 1) (Print 42 80))
							((> local123 5) (localproc_0f1f 42 79) (event claimed: 1))
							((or (Said '<blue') (Said '/(cable<blue)'))
								(if (== blueWireCut 0)
									(localproc_0f1f 42 83)
								else
									(localproc_0f1f 42 84)
									(newProp_4 cel: 0)
									(= local110 1)
								)
							)
							((or (Said '<white') (Said '/(cable<white)'))
								(if (== whiteWireCut 0)
									(localproc_0f1f 42 83)
								else
									(localproc_0f1f 42 85)
									(newProp_5 cel: 0)
									(= local110 1)
								)
							)
							((or (Said '<yellow') (Said '/(cable<yellow)'))
								(if (== yellowWireCut 0)
									(localproc_0f1f 42 83)
								else
									(localproc_0f1f 42 86)
									(newProp_6 cel: 0)
									(if (!= local123 3)
										(= local110 1)
									else
										(= yellowWireCut 0)
										(++ local123)
										(SolvePuzzle 3)
									)
								)
							)
							((or (Said '<purple') (Said '/(cable<purple)'))
								(if (== purpleWireCut 0)
									(localproc_0f1f 42 83)
								else
									(localproc_0f1f 42 87)
									(newProp_7 cel: 0)
									(= local110 1)
								)
							)
							(else (event claimed: 1) (localproc_0f1f 42 65))
						)
					)
				)
			)
		)
	)
)

(instance doBoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(explosionSound play:)
				(SetMenu 513 112 0)
				(SetMenu 514 112 0)
				(curRoom drawPic: 300 style: 7)
				(cast eachElementDo: #hide)
				(RedrawCast)
				(ShakeScreen 10)
				(= seconds 3)
			)
			(1
				(EgoDead
					{You and 49 other people are now dead...because YOU screwed up!}
				)
			)
		)
	)
)

(instance doBlowUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= local123 6) (curRoom newRoom: 44))
			)
		)
	)
)
