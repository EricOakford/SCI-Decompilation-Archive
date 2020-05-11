;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room43 0
)
(synonyms
	(dolphin dolphin)
)

(local
	gEgoViewer
	pelicanState
	local2
	local3
	pelican
	newAct_4
	dolphin
	glint
	whistle
	sandCastle
	newProp_3
	local11
	local12
	local13
	[local14 2]
	cloud
)
(instance whistleSound of Sound
	(properties)
)

(instance dolphinTheme of Sound
	(properties
		number 35
	)
)

(instance Room43 of Room
	(properties)
	
	(method (init)
		(if isNightTime (= picture 143) else (= picture 43))
		(= south (= north (= west (= east 31))))
		(= horizon 78)
		(= isIndoors FALSE)
		(= oldHorizon horizon)
		(super init:)
		(if (ego has: iDeadFish)
			(Load VIEW 312)
			(Load VIEW 311)
			(Load VIEW 310)
			(Load VIEW 524)
			(Load VIEW 306)
			(Load SOUND 35)
			(Load SOUND 76)
		)
		(Load VIEW 320)
		(Load VIEW 321)
		(Load VIEW 15)
		(Load VIEW 670)
		(Load VIEW 518)
		(self setRegions: WATER GULL)
		((View new:)
			view: 670
			posn: 50 142
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 670
			loop: 1
			cel: 2
			posn: 151 174
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 670
			loop: 2
			posn: 284 183
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 670
			loop: 3
			cel: 2
			posn: 301 145
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 670
			loop: 4
			posn: 24 74
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 670
			loop: 5
			cel: 1
			posn: 258 77
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((= cloud (Prop new:))
			view: 670
			posn: 50 142
			setPri: 0
			ignoreActors:
			cycleSpeed: 2
			init:
			setScript: waveScript
		)
		(if (or (ego has: iSilverWhistle) (ego has: iDeadFish)) (= local11 1))
		(cond 
			((and (== global136 101) (== global137 100)) (ego posn: 13 91))
			((and (== global136 99) (== global137 100)) (ego posn: 294 84))
			((and (== global136 100) (== global137 99)) (ego posn: 38 171))
			((and (== global136 100) (== global137 101)) (ego posn: (ego x?) (+ horizon (ego yStep?) 1)))
		)
		(= sandCastle (Prop new:))
		(sandCastle setScript: deathTimer init: hide:)
		;the sand castle is in view 524, loop 2. It seems to have been used for debugging the death timer.
		((= newProp_3 (Prop new:)) init: hide:)
		(if ((Inventory at: iSilverWhistle) ownedBy: 207)
			(= pelican (Actor new:))
			(pelican
				ignoreHorizon:
				posn: 118 73
				view: 320
				loop: 0
				setPri: 7
				illegalBits: 0
				ignoreActors:
				setCycle: Forward
				cycleSpeed: 1
				xStep: 5
				yStep: 4
				init:
			)
			(pelican setScript: pelActions)
		)
		(if ((Inventory at: iSilverWhistle) ownedBy: 43)
			((= whistle (Actor new:))
				posn: 137 106
				ignoreActors:
				illegalBits: 0
				view: 524
				setLoop: 1
				setCel: 0
				setCycle: 0
				init:
				stopUpd:
			)
			(whistle setScript: whistleActions)
			(whistleActions changeState: 1)
		)
		(ego view: 8 setStep: 3 2 setScript: bridleActions init:)
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose 84)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look[<around][/room]') (Print 43 0))
					((Said 'bathe')
						(cond 
							((== (ego view?) 2) (Print 43 1))
							((== (ego view?) 8) (Print 43 2))
							(
								(or
									(== (ego view?) 5)
									(== (ego view?) 6)
									(== (ego view?) 7)
								)
								(Print 43 3)
							)
							(else (Print 43 4))
						)
					)
					((Said 'get,drink[/drink,water]')
						(cond 
							((!= currentStatus egoNormal) (Print 43 5))
							(
								(or
									(& (= local13 (IsObjectOnControl ego 12)) $0008)
									(& local13 $0800)
									(& local13 $0002)
									(& local13 $0200)
								)
								(= oldEgoScript (ego script?))
								(ego setScript: drinking)
								(drinking changeState: 1)
							)
							(else (Print 800 1))
						)
					)
					((Said 'fish') (Print 43 6))
					((Said 'get,capture/fish') (Print 43 6))
					((Said 'look,find/tamir') (Print 43 7))
					((Said 'look>')
						(cond 
							((Said '/fish')
								(if (ego has: iDeadFish)
									((Inventory at: iDeadFish) showSelf:)
								else
									(Print 43 6)
								)
							)
							((Said '/dolphin')
								(cond 
									((cast contains: dolphin) (Print 43 8))
									((== (ego view?) 312) (Print 43 9))
									(else (Print 43 10))
								)
							)
							((Said '/island') (Print 43 11))
							((Said '<under/water,ocean')
								(if (== (ego view?) 2)
									(Print 43 12)
								else
									(Print 43 13)
								)
							)
							((Said '/ocean,water') (Print 43 14))
							((Said '/beach') (Print 43 15))
							(
								(or
									(Said '/dirt')
									(Said '<down')
									(Said '<in/boat,shipwreck')
								)
								(cond 
									(
										(and
											(ego inRect: 218 107 254 116)
											((Inventory at: iGoldenBridle) ownedBy: 43)
										)
										;how is anyone supposed to know that the bridle is here?!
										(theGame changeScore: 3)
										(bridleActions changeState: 1)
									)
									((cast contains: whistle) (Print 43 16))
									(
										(and
											(ego inRect: 194 97 310 135)
											((Inventory at: iGoldenBridle) ownedBy: 43)
										)
										(Print 43 17)
									)
									(else (Print 43 18))
								)
							)
							((Said '/forest,flora,palm') (Print 43 19))
							((Said '/coconut') (Print 43 20))
							((Said '/boat,shipwreck')
								(Print
									(Format @str 43 21
										(if ((Inventory at: iGoldenBridle) ownedBy: 43)
											;okay, there's some hint there...
											{You see a glint coming from one of the wrecked boats on the beach.}
										else
											{_}
										)
									)
								)
							)
							((Said '/pelican,bird') (if (!= pelicanState 4) (Print 43 22) else (Print 43 23)))
						)
					)
					((Said 'climb/forest,palm') (Print 43 24))
					((Said 'deliver,throw,feed/fish')
						(if (ego has: iDeadFish)
							(if (== (ego view?) 2)
								(switch pelicanState
									(1
										(if (< (ego distanceTo: pelican) 80)
											(if (> (ego x?) 121)
												(theGame changeScore: 4)
												(pelActions changeState: 20)
											else
												(Print 43 25)
											)
										else
											(Print 800 1)
										)
									)
									(2 (Print 43 26))
									(3 (Print 43 27))
									(else  (Print 43 28))
								)
							else
								(Print 43 28)
							)
						else
							(Print 43 29)
						)
					)
					((Said 'get,whistle/whistle')
						(cond 
							(((Inventory at: iSilverWhistle) ownedBy: 43)
								(if (< (ego distanceTo: whistle) 15)
									(bridleActions changeState: 10)
									(theGame changeScore: 2)
									(ego get: iSilverWhistle)
								else
									(Print 800 1)
								)
							)
							((ego has: iSilverWhistle) (Print 43 30))
							(else (Print 43 10))
						)
					)
					((Said 'mount/dolphin')
						(if (cast contains: dolphin)
							(if (< (ego distanceTo: dolphin) 14)
								(User canControl: FALSE)
								(= inCutscene TRUE)
								(theGame changeScore: 2)
								(dolphActions changeState: 10)
							else
								(Print 800 1)
							)
						else
							(Print 43 31)
						)
					)
					((Said 'play,whistle,blow[/whistle]')
						(if (ego has: iSilverWhistle)
							(sounds eachElementDo: #stop 0)
							(whistleSound number: 76 play:)
							(if (not dolphin)
								(if (== blewWhistle FALSE)
									(theGame changeScore: 2)
									(= blewWhistle TRUE)
								)
								((= dolphin (Actor new:)) x: -1000 y: 1000 init:)
								(curRoom setScript: dolphActions)
							else
								(Print 43 32)
							)
						else
							(Print 43 33)
						)
					)
					((Said 'converse>')
						(cond 
							(
								(and
									(cast contains: pelican)
									(pelican inRect: 0 0 319 189)
									(Said '[/pelican,bird]')
								)
								(Print 43 34)
								(event claimed: TRUE)
							)
							(
							(and (cast contains: dolphin) (Said '[/dolphin]')) (Print 43 35) (event claimed: TRUE))
							((Said '/bird,gull,gull') (event claimed: FALSE))
							((Said '[/!*]') (Print 43 36))
						)
					)
					((Said 'feed/pelican,bird')
						(if (and (cast contains: pelican) (!= pelicanState 4))
							(Print 43 37)
						else
							(Print 43 10)
						)
					)
					((Said 'get/pelican,bird')
						(if (and (cast contains: pelican) (!= pelicanState 4))
							(Print 43 38)
						else
							(Print 43 10)
						)
					)
					((Said 'capture/pelican,bird')
						(if (and (cast contains: pelican) (!= pelicanState 4))
							(Print 43 39)
						else
							(Print 43 10)
						)
					)
					((Said 'kiss')
						(cond 
							((and (cast contains: pelican) (!= pelicanState 4)) (Print 43 39))
							((cast contains: dolphin) (Print 43 40))
							(else (event claimed: FALSE))
						)
					)
					((Said 'deliver>')
						(if (= inventorySaidMe (inventory saidMe:))
							(if (ego has: (inventory indexOf: inventorySaidMe))
								(cond 
									(
										(or
											(and (!= pelicanState 4) (cast contains: pelican))
											(Said '/pelican,bird')
										)
										(Print 43 41)
									)
									(
									(and (cast contains: dolphin) (Said '/dolphin')) (Print 43 42))
									(else (Print 43 43) (event claimed: TRUE))
								)
							else
								(DontHave)
							)
						)
					)
					((Said 'get,capture/dolphin')
						(cond 
							((cast contains: dolphin) (Print 43 44))
							((== (ego view?) 312) (Print 43 45))
							(else (Print 43 10))
						)
					)
					((Said 'deliver>')
						(if
							(and
								(= inventorySaidMe (inventory saidMe:))
								(ego has: (inventory indexOf: inventorySaidMe))
							)
							(Print 43 46)
						else
							(Print 43 47)
						)
					)
					((Said 'pat/dolphin')
						(if (cast contains: dolphin)
							(if (< (ego distanceTo: dolphin) 12)
								(Print 43 48)
							else
								(Print 800 1)
							)
						else
							(Print 43 10)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance bridleActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego get: iGoldenBridle setMotion: 0)
				(= gEgoViewer (ego viewer?))
				(ego
					viewer: 0
					view: 21
					loop: 1
					cel: 255
					setCycle: EndLoop self
				)
			)
			(2
				(= gotItem TRUE)
				(ego setCycle: BegLoop self)
			)
			(3
				(Print 43 49 #at -1 10)
				(ego viewer: gEgoViewer view: 2 setCycle: Walk)
				(HandsOn)
			)
			(10
				(HandsOff)
				(= gEgoViewer (ego viewer?))
				(Face ego whistle)
				(ego
					viewer: 0
					view: 21
					loop: 1
					cel: 255
					setCycle: EndLoop self
				)
			)
			(11
				(whistle dispose:)
				(glint dispose:)
				(= gotItem TRUE)
				(ego setCycle: BegLoop self)
			)
			(12
				(ego viewer: gEgoViewer view: 2 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)

(instance pelActions of Script
	(properties)
	
	(method (doit)
		(if
		(and (== pelicanState 1) (ego inRect: 95 92 140 110))
			(pelActions changeState: 10)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: pelican)
					(= pelicanState 1)
					(newProp_3 setScript: pelMovement)
					((ScriptID 0 5) setReal: self 30)
				)
			)
			(1 (self changeState: 10))
			(10
				((ScriptID 0 5) dispose: delete:)
				(++ local2)
				(newProp_3 setScript: 0)
				(= pelicanState 2)
				(pelican
					view: 321
					setLoop: 2
					setCel: -1
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(11
				(pelican
					setLoop: 0
					cel: 0
					setCycle: Forward
					setMotion: MoveTo 339 20 self
				)
			)
			(12
				(pelican stopUpd:)
				(= pelicanState 4)
				((ScriptID 0 5) setReal: self 1 1)
			)
			(13
				(if
				(and (not (ego inRect: 192 108 237 125)) (< local2 3))
					(= pelicanState 2)
					(pelican
						startUpd:
						posn: -20 20
						setCycle: Forward
						setMotion: MoveTo 118 74 self
					)
				else
					(self changeState: 11)
				)
			)
			(14
				(pelican loop: 1 cel: 255 setCycle: EndLoop self)
			)
			(15
				(pelican view: 320 setLoop: 0 cycleSpeed: 1 setCycle: Forward)
				(= pelicanState 1)
				(newProp_3 setScript: pelMovement)
			)
			(20
				(HandsOff)
				(if (IsObject (ScriptID 0 5))
					((ScriptID 0 5) dispose: delete:)
				)
				(whistleSound number: 63 play:)
				(newProp_3 setScript: 0)
				(= pelicanState 3)
				(Face ego pelican)
				(= gEgoViewer (ego viewer?))
				(ego viewer: 0 loop: (& (ego loop?) $0001))
				(ego
					view: 15
					cel: 255
					setMotion: 0
					loop: (& (ego loop?) $0001)
					setCycle: EndLoop self
				)
			)
			(21
				(= newAct_4 (Actor new:))
				(if (ego loop?)
					(newAct_4 posn: (- (ego x?) 10) (- (ego y?) 15) init:)
				else
					(newAct_4 posn: (+ (ego x?) 10) (- (ego y?) 15) init:)
				)
				(newAct_4
					view: 306
					ignoreActors:
					illegalBits: 0
					ignoreHorizon:
					setPri: 6
					setLoop: 0
					cel: 0
					setCycle: Forward
					setMotion: MoveTo (+ (pelican x?) 8) (- (pelican y?) 7) self
				)
				(ego viewer: gEgoViewer view: 2 setCycle: Walk)
				(pelican setLoop: 1 setCel: -1 cel: 255 setCycle: EndLoop)
			)
			(22
				(= whistle (Actor new:))
				(whistle setScript: whistleActions)
				(whistle
					posn: 123 63
					ignoreActors:
					illegalBits: 0
					view: 524
					setLoop: 0
					cel: 0
					setMotion: MoveTo 137 106 whistleActions
					yStep: 10
					setCycle: Forward
					init:
				)
				(pelican setLoop: 2 cel: 255 setCycle: EndLoop self)
				(newAct_4 dispose:)
				((Inventory at: iSilverWhistle) moveTo: 43)
			)
			(23
				(Print 43 50)
				((Inventory at: iDeadFish) moveTo: 207)
				(= local2 99)
				(HandsOn)
				(self changeState: 10)
			)
		)
	)
)

(instance whistleActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(whistle setCel: 0 setLoop: 1 setCycle: 0 stopUpd:)
				(= glint (Prop new:))
				(glint
					view: 518
					setLoop: 4
					ignoreActors:
					posn: (whistle x?) (whistle y?)
					init:
				)
				(self changeState: 2)
			)
			(2
				(glint cel: 255 setCycle: EndLoop self show:)
			)
			(3
				(glint hide:)
				(= seconds (Random 3 10))
			)
			(4 (self changeState: 2))
		)
	)
)

(instance dolphActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(sounds eachElementDo: #stop 0)
				(dolphinTheme play:)
				(dolphin
					posn: 284 71
					view: 310
					loop: 0
					cel: 0
					ignoreHorizon:
					setCycle: EndLoop self
					init:
				)
			)
			(2
				(dolphin view: 311 posn: 283 87 loop: 3 setCycle: Forward)
				(Print 43 51)
			)
			(10
				(dolphin dispose:)
				(= currentStatus egoRidingDolphin)
				(ego
					view: 312
					loop: 0
					setCycle: Forward
					setMotion: MoveTo 339 (ego y?)
				)
			)
		)
	)
)

(instance deathTimer of Script
	(properties)
	
	(method (doit)
		(if
			(and
				local12
				(<= state 2)
				(or
					(ego inRect: 140 98 196 118)
					(ego inRect: 146 118 258 150)
				)
			)
			(self changeState: 10)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local11 (= seconds 240) else (= seconds 120))
			)
			(1 (= local12 1))
			(10
				(HandsOff)
				(ego
					illegalBits: -2
					setAvoider: Avoider
					setMotion: MoveTo 184 130 self
				)
			)
			(11
				(ego
					setMotion: 0
					viewer: 0
					view: 21
					loop: 2
					cel: 255
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(12
				(ego view: 83 loop: 0 cel: 0)
				(= seconds 4)
			)
			(13 (ego cel: 1) (= seconds 5))
			(14 (ego cel: 2) (= seconds 6))
			(15 (ego cel: 3) (= seconds 7))
			(16
				(Print 43 52 #at -1 10)
				(= seconds 6)
			)
			(17 (= dead TRUE))
		)
	)
)

(instance drinking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= gEgoViewer (ego viewer?))
				(ego viewer: 0 view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(= timedMessage (Print 43 5 #at -1 10 #dispose))
				(= seconds 5)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(cls)
				(ego view: 2 setCycle: Walk)
				(ego script: oldEgoScript viewer: gEgoViewer)
				(HandsOn)
			)
		)
	)
)

(instance pelMovement of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== pelicanState 1) (pelican setCycle: Forward))
				(= seconds (Random 3 7))
			)
			(1
				(if (== pelicanState 1)
					(pelican setCycle: 0)
					(= seconds (Random 3 7))
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance waveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 2 6)))
			(1
				(cloud loop: 0 cel: 0 posn: 50 142 setCycle: EndLoop self)
				(= state 6)
			)
			(2
				(cloud loop: 1 cel: 2 posn: 151 174 setCycle: EndLoop self)
				(= state 6)
			)
			(3
				(cloud loop: 2 cel: 0 posn: 284 183 setCycle: EndLoop self)
				(= state 6)
			)
			(4
				(cloud loop: 3 cel: 2 posn: 301 145 setCycle: EndLoop self)
				(= state 6)
			)
			(5
				(cloud loop: 4 cel: 0 posn: 24 74 setCycle: EndLoop self)
				(= state 6)
			)
			(6
				(cloud loop: 5 cel: 1 posn: 258 77 setCycle: EndLoop self)
			)
			(7
				(cloud posn: 999 999)
				(= cycles (Random 2 10))
				(= state (Random 1 6))
			)
		)
	)
)
