;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include system.sh)
(include game.sh)
(include keys.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm60 0
)

(local
	storedInventory
	local1
	collision
	newProp
	newProp_2
	newProp_3
	local6
	local7
	local8
	local9
	local10
	car
	newProp_4
	moore
	van
	showedDivingCertificate
	local16
	local17
	local18
)
(procedure (localproc_2126)
	(vanDoor
		view: 154
		setLoop: 0
		setCel: 2
		posn: 290 175
		ignoreActors:
		init:
	)
)

(instance vanDoor of Actor
	(properties)
)

(instance bains of Actor
	(properties)
)

(instance vanBlock of Block
	(properties
		top 163
		left 234
		bottom 178
		right 330
	)
)

(instance bainsMusic of Sound
	(properties)
)

(instance carSound of Sound
	(properties)
)

(instance bainsShot of Sound
	(properties
		number 41
		priority 14
	)
)

(instance diverScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= van (Actor new:))
					view: 154
					setLoop: 0
					setCel: 0
					setStep: 8
					posn: (if (== diverState 4) -66 else 283) 174
					ignoreActors:
					init:
					setCycle: 0
				)
				(ego observeBlocks: vanBlock stopUpd:)
				(switch diverState
					(4
						(HandsOff)
						(ego loop: 2 cel: 3)
						(van setMotion: MoveTo 283 174 self)
					)
					(6
						(van cel: 1 addToPic:)
						(localproc_2126)
						(self cue:)
					)
					(14
						(if (not global192)
							(User canControl: 0)
							(ego view: 17)
							(van cel: 1 addToPic:)
							(localproc_2126)
						else
							(van cel: 0 addToPic:)
						)
					)
					(16
						(if (not global192)
							(User canControl: 0)
							(ego view: 17)
							(van cel: 1 addToPic:)
							(localproc_2126)
						else
							(van cel: 0 addToPic:)
						)
					)
					(8
						(van cel: 1 addToPic:)
						(moore posn: 223 139 setMotion: Follow ego 500)
						(localproc_2126)
						(self cue:)
					)
					(else 
						(van cel: 0 addToPic:)
						(self cue:)
					)
				)
			)
			(1
				(if (< diverState 6)
					(= diverState 5)
					(= gunNotNeeded 1)
					(= gunFireState 3)
					(van loop: 5 cel: 1 stopUpd: addToPic:)
					(HandsOn)
					(localproc_2126)
				)
				(moore setCycle: Walk startUpd: loop: 0)
				(cond 
					((== prevRoomNum 66)
						(moore posn: 223 139)
						(= gunFireState 3)
						(diverScript changeState: 27)
					)
					((>= diverState 11) (moore posn: -40 77))
					((== diverState 8) (Print 60 0))
					(else
						(= gunFireState gunPROHIBITED)
						(HandsOff)
						(moore
							loop: 0
							setStep: 3 2
							ignoreActors:
							illegalBits: 0
							posn: 288 162
							setMotion: MoveTo 170 162 self
						)
					)
				)
			)
			(2
				(HandsOn)
				(moore ignoreActors: 0)
				(vanDoor stopUpd:)
				(moore setMotion: Follow ego 500)
				(if (== diverState 5) (Print 60 1 #at -1 70 #draw))
				(if (< diverState 8) (= diverState 6))
			)
			(3
				(HandsOff)
				(User canInput: 1)
				(= diverState 7)
				(Print 60 2 #at -1 70 #draw)
				(Print 60 3)
				(if (or (not (ego has: 7)) (not (Btst 33)))
					(Print 60 4)
				)
				(= showedDivingCertificate 1)
				(= local16 60)
			)
			(4
				(Print 60 5 #at -1 70)
				(= local16 60)
			)
			(5
				(= showedDivingCertificate 0)
				(Print 60 6)
				(self changeState: 8)
			)
			(6
				(= local17 1)
				(Print 60 7 #icon 164 0 0)
				(Print 60 8 #at -1 70)
				(self changeState: 9)
			)
			(8
				(= local17 0)
				(Print 60 9 #at -1 70)
				(Print 60 8 #at -1 70)
				(self cue:)
			)
			(9
				(HandsOff)
				(moore
					setStep: 3 2
					ignoreActors: 0
					observeBlocks: vanBlock
					setAvoider: (Avoider new:)
					setMotion: MoveTo 230 184 self
				)
			)
			(10
				(moore
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 289 186 self
				)
			)
			(11
				(vanDoor setMotion: MoveTo 270 175 self)
			)
			(12
				(vanDoor stopUpd:)
				(moore setMotion: MoveTo 290 182 self)
			)
			(13
				(moore setLoop: 3)
				(= diverState 8)
				(self cue:)
			)
			(14
				(moore hide:)
				(vanDoor setMotion: MoveTo 290 175)
				(= seconds 5)
			)
			(15
				(vanDoor setMotion: MoveTo 270 175 self)
			)
			(16
				(moore
					view: 22
					posn: 289 184
					loop: 2
					cel: 1
					show:
					setMotion: MoveTo 289 188 self
					ignoreActors: 0
				)
			)
			(17
				(Print 60 10 #time 4)
				(vanDoor setMotion: MoveTo 290 175)
				(moore
					 setLoop: -1
					 setMotion: MoveTo 220 139 self
				)
			)
			(18
				(vanDoor stopUpd:)
				(self cue:)
			)
			(19
				(if local17
					(HandsOn)
					(moore loop: 0 stopUpd:)
					(ego loop: 1)
					(Print 60 11 #at -1 50 #draw)
				else
					(Print 60 12 #at -1 50)
					(moore setMotion: MoveTo 180 85 self)
					(= global157 500)
				)
			)
			(20
				(moore
					view: 98
					cel: 0
					setLoop: 7
					setCycle: EndLoop
					posn: 178 63
					setMotion: MoveTo 180 54 self
				)
				(= diverState 10)
				(= global192 1)
				(Print 60 13 #at -1 45 #draw)
				(HandsOn)
			)
			(21 (moore stopUpd:))
			(22
				(HandsOff)
				(ego
					setLoop: 3
					setMotion: MoveTo 289 187 self
					ignoreActors:
					illegalBits: 0
				)
			)
			(23
				(vanDoor startUpd: setMotion: MoveTo 270 175 self)
			)
			(24
				(ego setMotion: MoveTo 289 185 self)
			)
			(25
				(ego hide:)
				(vanDoor setMotion: MoveTo 290 175 self)
			)
			(26
				(HandsOn)
				(curRoom newRoom: 66)
			)
			(27
				(HandsOff)
				(vanDoor setMotion: MoveTo 270 175 self)
			)
			(28
				(ego
					view: 17
					posn: 289 186
					loop: 5
					cel: 1
					show:
					illegalBits: 0
					setMotion: MoveTo 289 188 self
				)
			)
			(29
				(vanDoor setMotion: MoveTo 290 175)
				(ego setMotion: MoveTo 220 188 self)
			)
			(30
				(ego setMotion: MoveTo 216 150 self)
			)
			(31
				(ego setMotion: MoveTo 212 81 self)
				(moore illegalBits: 0 setMotion: MoveTo 180 78)
			)
			(32
				(Print 60 14 #at -1 45)
				(moore
					view: 98
					cel: 0
					setLoop: 7
					setCycle: EndLoop
					posn: 178 63
					setMotion: MoveTo 180 54
				)
				(ego
					view: 98
					posn: 211 70
					cel: 0
					setLoop: 0
					setStep: 1 1
					cycleSpeed: 2
					setMotion: MoveTo 210 66
					setCycle: EndLoop self
				)
			)
			(33
				(moore stopUpd:)
				(ego setLoop: 1 setCycle: Forward)
				(= seconds 2)
			)
			(34
				(ego setLoop: 2 setCycle: EndLoop self)
			)
			(35
				(ego setLoop: 3 setCycle: EndLoop self)
			)
			(36
				(if
				(and (== scubaTankOxygen 2200) (Btst 7) (Btst 6))
					(SolvePuzzle 6)
				)
				(ego setLoop: 4 setCycle: Forward)
				(= seconds 2)
			)
			(37
				(ego
					setLoop: 6
					setMotion: MoveTo 210 63
					setCycle: EndLoop self
				)
			)
			(38
				(ego
					setLoop: 7
					setMotion: MoveTo 210 50
					setCycle: EndLoop self
				)
			)
			(39
				(HandsOn)
				(ego hide: setLoop: -1)
				(curRoom newRoom: 63)
			)
			(40
				(= diverState 13)
				(moore
					posn: 50 78
					view: 22
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 100 78 self
				)
			)
			(41
				(= removedBodyFromRiver 1)
				(Print 60 15)
				(moore setMotion: MoveTo -30 90)
			)
			(42
				(= global192 1)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 180 (if (< (ego y?) 112) 112 else (ego y?)) self
				)
			)
			(43
				(if removedBodyFromRiver
					(Print 60 16)
				else
					(Print 60 17)
				)
				(ego setMotion: MoveTo 180 188 self)
			)
			(44
				(ego setMotion: MoveTo 288 188 self)
			)
			(45
				(ego
					setLoop: 3
					setMotion: MoveTo 285 188 self
					ignoreActors:
					illegalBits: 0
				)
			)
			(46
				(vanDoor setMotion: MoveTo 270 175 self)
			)
			(47
				(ego setMotion: MoveTo 289 182 self)
			)
			(48
				(ego hide:)
				(vanDoor setMotion: MoveTo 290 175 self)
			)
			(49
				(Print 60 18)
				(= storedInventory 0)
				(while (<= storedInventory 37)
					(if (InRoom storedInventory 66)
						(ego get: storedInventory)
					)
					(++ storedInventory)
				)
				(= seconds 6)
			)
			(50
				(ego setLoop: -1)
				(vanDoor setMotion: MoveTo 270 175 self)
			)
			(51
				(ego
					view: 0
					posn: 289 184
					loop: 5
					cel: 1
					show:
					setMotion: MoveTo 289 188 self
					illegalBits: -32768
					ignoreActors:
				)
			)
			(52
				(HandsOn)
				(vanDoor setMotion: MoveTo 290 175 self)
			)
			(53
				(vanDoor addToPic:)
				(if (not removedBodyFromRiver) (self changeState: 40))
			)
		)
	)
)

(instance rm60 of Room
	(properties
		picture 60
		style $0007
	)
	
	(method (init)
		(super init:)
		(self setLocales: regFieldKit regCove)
		(Load VIEW 0)
		(Load VIEW 4)
		(Load VIEW 6)
		(Load VIEW 20)
		(Load VIEW 90)
		(if
			(and
				(not shotAtBainsInCove)
				(== global111 3)
			)
			(Load VIEW 53)
			(Load VIEW 97)
			(Load VIEW 14)
			(Load VIEW 15)
			(Load VIEW 76)
			(Load SOUND 33)
			(Load SOUND 41)
			(Load SOUND 41)
			(Load SOUND 39)
		)
		(Load VIEW 154)
		(Load VIEW 22)
		(Load VIEW 98)
		(Load VIEW 17)
		(Load VIEW 21)
		((= moore (Actor new:))
			view: 21
			posn: -100 0
			init:
			stopUpd:
		)
		(= gunNotNeeded (!= gamePhase phaseCOVE))
		(if (and (not shotAtBainsInCove) (== global111 3))
			(= gunFireState 0)
		else
			(= gunFireState 2)
		)
		(if (< global111 3) (= diverState 0))
		(NormalEgo)
		(ego
			illegalBits: cWHITE;-16384
			init:
		)
		((= keith (Actor new:))
			view: 20
			posn: 1000 1000
			illegalBits: cWHITE
			init:
			stopUpd:
		)
		(if (> diverState 2) (= global187 0))
		((= newProp (Prop new:))
			view: 90
			posn: 74 97
			cel: (if (== global208 1) 4 else 0)
			ignoreActors:
			setPri: -1
			init:
			stopUpd:
		)
		((= newProp_2 (Prop new:))
			view: 90
			posn: 60 131
			cel: (if (== global208 2) 4 else 0)
			ignoreActors:
			setPri: -1
			init:
			stopUpd:
		)
		((= newProp_3 (Prop new:))
			view: 90
			posn: 10 165
			cel: (if (== global208 3) 4 else 0)
			ignoreActors:
			setPri: -1
			init:
			stopUpd:
		)
		(switch prevRoomNum
			(66 (ego hide:))
			(61
				(if (not shotAtBainsInCove)
					(bainsMusic number: 38 loop: -1 play:)
				)
				(ego posn: 300 (ego y?) setMotion: MoveTo -10 (ego y?))
				(User prevDir: 7)
				(if (and (== currentCar 13) (== diverState 0))
					(keith
						view: 20
						posn: (+ (ego x?) 90) (ego y?)
						setAvoider: Avoider
						setMotion: Follow ego 87
						setCycle: Walk
					)
				)
				(if (and (not shotAtBainsInCove) (== global111 3))
					(bains
						view: 15
						loop: 0
						cel: 2
						posn: -12 124
						setStep: 12 2
						illegalBits: 0
						setScript: bainsScript
						init:
					)
				)
			)
			(62
				(ego posn: 20 (ego y?) setMotion: MoveTo 350 (ego y?))
				(User prevDir: 3)
				(if (== currentCar 13)
					(cond 
						(global187
							(if (not bainsInCoveTimer) (Bset 55))
							(if (not (Btst 55))
								(Bset 55)
								(= diverState 1)
								(keith
									view: 20
									posn: 138 102
									setCycle: Walk
									setMotion: MoveTo 360 150
								)
								(Print 60 19)
							)
						)
						((== diverState 0)
							(keith
								view: (if bainsInCoveState 53 else 20)
								posn: 181 115
								setCycle: Walk
								setAvoider: Avoider
								setMotion: Follow ego 60
							)
						)
					)
				)
			)
		)
		(self setScript: rm60Script)
	)
	
	(method (doit)
		(cond 
			((> local16 1) (-- local16))
			((== local16 1)
				(= local16 0)
				(switch (diverScript state?)
					(3 (diverScript changeState: 4))
					(4 (diverScript changeState: 5))
				)
			)
		)
		(if
		(and showedDivingCertificate (ego has: 7) (Btst 33))
			(= showedDivingCertificate 0)
			(diverScript changeState: 6)
		)
		(super doit:)
	)
	
	(method (dispose)
		(diverScript dispose:)
		(bainsScript dispose:)
		(super dispose:)
	)
)

(instance rm60Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(local18 0)
			((> (ego y?) 205)
				(switch (Random 0 2)
					(0 (Print 60 20))
					(1 (Print 60 21))
					(2 (Print 60 22))
				)
				(ego setMotion: MoveTo (ego x?) 176)
			)
			((== (ego edgeHit?) 2) (cSound fade:) (curRoom newRoom: 61))
			((== (ego edgeHit?) 4) (curRoom newRoom: 62))
			(
			(and (== diverState 8) (ego inRect: 282 175 299 184)) (diverScript changeState: 22))
			((== diverState 12) (diverScript changeState: 40))
			(
				(and
					(>= (ego x?) 10)
					(== (ego view?) 17)
					(== prevRoomNum 62)
					(not global192)
				)
				(diverScript changeState: 42)
			)
			(
				(and
					(not shotAtBainsInCove)
					(== global111 3)
					(not local6)
					(< (ego x?) 240)
				)
				(= shotAtBainsInCove 1)
				(if (== currentCar 13)
					(keith view: 53 setMotion: MoveTo 181 112)
				)
				(bainsScript changeState: 1)
			)
			(
				(and
					(== diverState 3)
					(< (ego y?) 157)
					(> 257 (ego x?))
					(> (ego x?) 68)
				)
				(= diverState 4)
				(moore setScript: diverScript)
			)
			(bainsInCoveState
				(keith view: 53)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (>= diverState 5) (>= global111 3))
					(moore
						view: (if (>= diverState 8) 22 else 21)
						posn: (if (== diverState 12) 150 else -100) 126
						setCycle: Walk
						setScript: diverScript
					)
				)
			)
			(1
				(ego setMotion: 148 (+ (ego y?) 4))
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(= temp0 (event message?))
				(if
					(and
						(== (ego view?) 17)
						(or
							(== temp0 KEY_F6)
							(== temp0 KEY_F8)
							(== temp0 KEY_F10)
						)
					)
					(Print 60 23)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
			)
			(saidEvent
				(cond 
					((Said 'check,look/air,gauge,equipment')
						(if (!= (ego view?) 17)
							(Print 60 24)
						else
							(Print 60 25)
						)
					)
					((Said 'frisk/billfold') (event claimed: 0))
					((Said 'display/badge,billfold')
						(if showedDivingCertificate
							(Print 60 26)
						else
							(Print 60 27)
						)
					)
					((Said 'change[/cloth]') (Print 60 28))
					(
						(and
							showedDivingCertificate
							(Said 'find,get,display,frisk,look/card,certificate')
						)
						(Print 60 29)
					)
					((Said 'chat/cop,dude,diver,diver')
						(if
							(and
								(cast contains: moore)
								(< (ego distanceTo: moore) 40)
							)
							(Print 60 30)
						else
							(Print 60 31)
						)
					)
					((Said 'apprehend,chase/bains')
						(if (and (== global111 3) shotAtBainsInCove bainsInCoveState)
							(Print 60 32)
						else
							(Print 60 33)
						)
					)
					((or (Said '/police,freeze') (Said 'freeze'))
						(if (and (== global111 3) shotAtBainsInCove bainsInCoveState)
							(Print 60 34)
						else
							(Print 60 35)
						)
					)
					((Said 'look,beat,get,chat/bains,suspect')
						(cond 
							(local1 (Print 60 36))
							((and (== global111 3) shotAtBainsInCove bainsInCoveState) (Print 60 37))
							(else (Print 60 33))
						)
					)
					((Said 'look,read/heart,initials')
						(if (ego inRect: 0 114 60 145)
							(Print 60 38)
							(Print 60 39)
						else
							(Print 60 40)
						)
					)
					(
						(or
							(Said 'open/door,van')
							(Said 'enter,(get<in)/van,bus')
						)
						(if (and (> diverState 2) (>= global111 3))
							(if (== diverState 8) (Print 60 41) else (Print 60 42))
						else
							(Print 60 43)
						)
					)
					((Said 'get,drive,rob/van,bus') (Print 60 44))
					(
						(Said
							'find,get,frisk,look/clue,blood,mark,footprint,(print<feet)'
						)
						(Print 60 45)
					)
					((Said 'look>')
						(cond 
							((Said '/diver,diver,cop,dude')
								(cond 
									(
									(and (cast contains: moore) (moore inRect: 0 0 320 190)) (Print 60 46))
									((Said '/dude') (event claimed: 0))
									(else (Print 60 47))
								)
							)
							((Said '/auto') (if local1 (Print 60 48) else (Print 60 49)))
							((Said '/plate')
								(cond 
									(local1 (Print 60 50))
									((and (>= diverState 5) (== gamePhase 5)) (Print 60 51))
									(else (Print 60 52))
								)
							)
							((Said '/van,bus,pane,door')
								(if (and (> diverState 2) (>= global111 3))
									(Print 60 53)
								else
									(Print 60 43)
								)
							)
							((Said '<behind/tree') (Print 60 54))
							((Said '<below/bush') (Print 60 55))
							((Said '/tree')
								(if (ego inRect: 0 117 62 147)
									(Print 60 56)
								else
									(Print 60 57)
								)
							)
							((Said '[<at,around][/(!*,cove,area)]') (Print 60 58))
						)
					)
					((Said '/clue') (Print 60 59))
					((Said '/bullet') (Print 60 60))
					(
						(or
							(Said 'answer,ask/[diver,dude]')
							(Said '[answer,ask]/diver,dude')
							(Said 'help,dive,swim,frisk,find')
							(Said '/swim,dive,help')
							(Said 'go,jump/lake,clearwater,water')
							(Said 'let//dive,swim,find,frisk,look,get')
							(Said 'let/look,frisk,find,dive,get,go')
						)
						(if (> diverState 5)
							(if (< (ego distanceTo: moore) 40)
								(if (== diverState 6)
									(diverScript changeState: 3)
								else
									(Print 60 33)
								)
							else
								(Print 60 61)
							)
						else
							(Print 60 62)
						)
					)
				)
			)
		)
	)
)

(instance bainsScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((> local8 1) (-- local8))
			((== local8 1) (self cue:))
		)
		(if global205
			(if (== local10 0)
				(if local7 (Print 60 63 #at -1 24))
				(if
					(and
						shotAtBainsInCove
						(or (not gunSightsAligned) (!= global205 4))
					)
					(Print 60 64 #at -1 20)
					(= local10 1)
				else
					(= local10 2)
					(= global208
						(cond 
							((< (ego y?) 114) 1)
							((< (ego y?) 148) 2)
							(else 3)
						)
					)
					(self changeState: 4)
				)
			)
			(= global205 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bains stopUpd:))
			(1
				(bainsMusic number: 33 loop: 1 play:)
				(bains
					view: 14
					posn:
						-10
						(cond 
							((and (> 135 (ego y?)) (> (ego y?) 112)) 119)
							((> (ego y?) 134) (- (ego y?) 8))
							(else 100)
						)
					setCycle: Walk
					setMotion:
						MoveTo
						(cond 
							((and (> 135 (ego y?)) (> (ego y?) 112)) 52)
							((> (ego y?) 134) 46)
							(else 82)
						)
						(cond 
							((and (> 135 (ego y?)) (> (ego y?) 112)) 119)
							((> (ego y?) 134) (- (ego y?) 4))
							(else 100)
						)
						self
				)
			)
			(2
				(bainsShot play:)
				(bains
					view: 15
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(if (== (++ local7) 3)
					(HandsOff)
					(self changeState: 6)
				)
				(= local8 10)
			)
			(3
				(if (not local6)
					(self changeState: 2)
				else
					(= local8 0)
				)
			)
			(4
				(= local6 1)
				(= bainsInCoveTimer 1200)
				(= bainsInCoveState 1)
				(bains
					view: 14
					loop: 1
					cel: 2
					setCycle: Walk
					setMotion: MoveTo -25 (bains y?)
				)
				(if (== (ego view?) 97)
					(self changeState: 8)
				else
					(switch global208
						(1
							(newProp setCycle: EndLoop self startUpd:)
						)
						(2
							(newProp_2 setCycle: EndLoop self startUpd:)
						)
						(3
							(newProp_3 setCycle: EndLoop self startUpd:)
						)
					)
				)
			)
			(5
				(newProp addToPic:)
				(newProp_2 addToPic:)
				(newProp_3 addToPic:)
				(Bset fGotPoints)
				(cond 
					(local7 (Print 60 65 #at -1 20))
					(shotAtBainsInCove (Print 60 66))
					(else (EgoDead 60 67))
				)
				(SolvePuzzle 4)
				(bains setScript: carScript)
			)
			(6
				(ego
					view: 97
					loop:
					(switch (ego loop?)
						(0 4)
						(3 4)
						(1 3)
						(2 3)
					)
					cel: 0
					cycleSpeed: 1
					init:
					setCycle: EndLoop self
					setMotion: 0
				)
			)
			(7
				(Print 60 68 #at -1 20)
				(self changeState: 4)
			)
			(8 (= seconds 2))
			(9
				(switch local10
					(1 (EgoDead 60 69))
					(else  (EgoDead 60 70))
				)
			)
		)
	)
)

(instance carScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and bainsInCoveState shotAtBainsInCove) (++ local9))
		(if (and bainsInCoveState (> local9 120))
			(carScript changeState: 1)
		)
		(if
			(and
				(< 10 (- (ego x?) (car x?)))
				(< (- (ego x?) (car x?)) 64)
				(> 14 (- (car y?) (ego y?)))
				(> (car x?) -64)
				(< (self state?) 5)
			)
			(self changeState: 5)
		)
		(newProp_4 posn: (- (car x?) 48) (+ (car y?) 6))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= car (Actor new:))
					view: 94
					setStep: 18 10
					setLoop: 1
					setCel: 0
					posn: -66 160
					init:
					illegalBits: 0
					ignoreActors:
					stopUpd:
				)
				((= newProp_4 (Prop new:))
					view: 94
					loop: 2
					cel: 0
					init:
					ignoreActors:
					stopUpd:
				)
			)
			(1
				(carSound number: 39 loop: 1 priority: 12 play:)
				(= local1 1)
				(keith setMotion: Follow car 500)
				(= bainsInCoveState 0)
				(car
					setMotion:
						MoveTo
						(ego x?)
						(if (> (ego y?) 140) (ego y?) else 140)
						self
					startUpd:
				)
				(newProp_4 setCycle: Forward startUpd:)
			)
			(2
				(car
					setMotion:
						MoveTo
						430
						(if (> (ego y?) 120) (+ (ego y?) 35) else 140)
						self
				)
			)
			(3
				(if (== currentCar 13)
					(Print 60 71)
					(keith setMotion: MoveTo 187 118 self)
					(= gunFireState 2)
				)
			)
			(4 (= local1 0))
			(5
				(car
					setMotion: MoveTo 430 (if (> (ego y?) 120) (+ (ego y?) 28) else 140)
				)
				(if (== currentCar 13)
					(keith
						illegalBits: -32768
						ignoreActors: 0
						setMotion: Follow ego 20
						startUpd:
					)
				)
				((= collision (Actor new:))
					view: 76
					posn: (ego x?) (ego y?)
					loop: 5
					cel: 0
					setMotion: 0
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
				(ego dispose:)
				(= local18 1)
			)
			(6
				(HandsOff)
				(collision
					setLoop: 6
					cel: 0
					setMotion: MoveTo (+ (collision x?) 20) (collision y?)
					setCycle: EndLoop self
				)
			)
			(7
				(collision setLoop: 7 setCel: 0 stopUpd: addToPic:)
				(= seconds 2)
			)
			(8
				(if (== currentCar 13)
					(keith loop: 1)
					(Print 60 72 #draw)
				)
				(= seconds 4)
			)
			(9
				(if (== currentCar 13) (EgoDead 60 73))
			)
		)
	)
)
