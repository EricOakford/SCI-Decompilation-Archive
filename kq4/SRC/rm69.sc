;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room69 0
)

(local
	ropeLadder
	coffin
	coffinLid
	mummy
	pandoraBox
	local5
	climbingLadder
	climbedDown
	mummyAwake
)
(instance fallMusic of Sound
	(properties
		number 51
	)
)

(instance mummyMusic of Sound
	(properties
		number 27
	)
)

(instance mummyBlock of Block
	(properties
		top 110
		left 140
		bottom 122
		right 182
	)
)

(instance ladderSpace of Block
	(properties
		top 146
		left 90
		bottom 152
		right 96
	)
)

(instance Room69 of Room
	(properties
		picture 69
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 520)
		(Load VIEW 522)
		(Load VIEW 195)
		(Load VIEW 196)
		(Load VIEW 197)
		(Load VIEW 4)
		(Load VIEW 38)
		(Load VIEW 519)
		(Load VIEW 683)
		(Load VIEW 44)
		(Load VIEW 41)
		(Load VIEW 40)
		(Load VIEW 42)
		(Load VIEW 37)
		(Load VIEW 39)
		(super init:)
		(= climbedDown FALSE)
		(= climbingLadder 0)
		(= isIndoors TRUE)
		((View new:)
			view: 522
			loop: 3
			cel: 0
			posn: 253 71
			addToPic:
		)
		((Prop new:)
			view: 522
			loop: 2
			cel: 0
			posn: 252 57
			init:
			setCycle: Forward
		)
		((= coffin (View new:))
			view: 520
			loop: 2
			cel: 0
			setPri: 7
			posn: 160 112
			init:
			stopUpd:
		)
		((= coffinLid (Prop new:))
			view: 520
			ignoreActors: TRUE
			setPri: 8
			loop: 0
			cel: 0
			posn: (- (coffin x?) 4) (coffin y?)
			init:
			stopUpd:
		)
		((= ropeLadder (Prop new:))
			view: 522
			setCel: (if ropeLadderLowered 255 else 0)
			setPri: 11
			ignoreActors: TRUE
			posn: 98 87
			init:
		)
		((= mummy (Actor new:))
			view: 195
			illegalBits: 0
			setPri: 7
			posn: (coffin x?) (coffin y?)
			init:
		)
		(if ((Inventory at: iPandorasBox) ownedBy: 69)
			((= pandoraBox (Actor new:))
				view: 519
				illegalBits: 0
				ignoreActors: 0
				posn: 260 171
				init:
			)
		)
		(ego
			posn: 41 86
			view: 4
			loop: 0
			ignoreActors: 1
			setStep: 4 1
			illegalBits: cWHITE
			setPri: 11
			init:
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040)
			(User canControl: TRUE canInput: TRUE)
			(ego setPri: -1)
			(curRoom newRoom: 18)
		)
		(if
			(and
				(& (ego onControl: 1) $0004)
				(== (ego script?) 0)
			)
			(User canControl: FALSE canInput: FALSE)
			(ego setScript: cryptFall)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look/room,crypt')
							)
							(Print
								(Format @str 69 0
									(if (== ropeLadderLowered FALSE)
										{A pile of rope is at the edge of the upper platform.}
									else
										{_}
									)
									(if (== ((Inventory at: iPandorasBox) owner?) 69)
										{You see an odd-looking, little chest here. It must be Pandora's Box.}
									else
										{_}
									)
								)
							)
						)
						((Said 'look>')
							(cond 
								((Said '/epitaph') (Print 69 1))
								(
									(and
										(Said '<in/casket[<elderly]')
										(< (ego distanceTo: coffin) 25)
									)
									(Print 69 2)
								)
								((Said '/casket') (Print 69 3))
								((or (Said '/hemp,ladder') (Said '/hemp[<<]')) (if (== ropeLadderLowered FALSE) (Print 69 4) else (Print 69 5)))
								((Said '/platform') (if (== ropeLadderLowered FALSE) (Print 69 4) else (Print 69 6)))
								((Said '/wall') (Print 69 7))
								((or (Said '/dirt') (Said '<down'))
									(if (== ((Inventory at: iPandorasBox) owner?) 69)
										(Print 69 8)
									else
										(Print 69 9)
									)
								)
								((Said '/mummy') (if mummyAwake (Print 69 10) else (Print 69 11)))
								((Said '/box[<pandora]')
									(cond 
										((== ((Inventory at: iPandorasBox) owner?) 69) (Print 69 8))
										((ego has: iPandorasBox) (event claimed: FALSE))
										(else (Print 69 12))
									)
								)
								(else (event claimed: FALSE))
							)
						)
						((Said 'get,move,lower/hemp,ladder,<')
							(cond 
								(ropeLadderLowered (Print 69 13))
								((> (ego distanceTo: ropeLadder) 25) (Print 800 1))
								(else
									(Print 69 14)
									(theGame changeScore: 2)
									(User canControl: FALSE canInput: FALSE)
									(ego setMotion: 0)
									(= climbingLadder 1)
									(ropeLadder setScript: DropRope)
								)
							)
						)
						((Said 'climb[/(hemp,ladder)>')
							(cond 
								((not ropeLadderLowered) (event claimed: TRUE) (Print 800 3))
								((or (Said '<down') (not climbedDown))
									(event claimed: TRUE)
									(if (> (ego distanceTo: ropeLadder) 20)
										(Print 800 1)
									else
										(ego setScript: DownLadder)
									)
								)
								((or (Said '<up') climbedDown)
									(event claimed: TRUE)
									(if (not (ego inRect: 99 142 120 158))
										(Print 800 1)
									else
										(ego ignoreBlocks: ladderSpace)
										(DownLadder changeState: 14)
									)
								)
							)
						)
						((Said 'get/box')
							(cond 
								((not climbedDown) (Print 69 15))
								((ego has: iPandorasBox) (Print 69 16))
								((!= ((Inventory at: iPandorasBox) owner?) 69) (Print 69 17))
								((> (ego distanceTo: pandoraBox) 20) (Print 800 1))
								(else (ego setScript: stoop))
							)
						)
						((Said 'place,drop,return/box')
							(cond 
								((not (ego has: iPandorasBox)) (Print 69 18))
								((not climbedDown) (Print 69 15))
								(else (ego setScript: putBack))
							)
						)
						((Said 'open/box')
							(cond 
								((not (ego has: iPandorasBox)) (Print 69 18))
								((not climbedDown) (Print 69 19))
								(else (event claimed: FALSE))
							)
						)
						((Said 'read/(epitaph,wall,casket,lid)') (Print 69 1))
						((Said 'get,rob,get,move/casket') (Print 69 20))
						((Said 'bang[<on]/casket')
							(if (< (ego distanceTo: coffin) 15)
								(Print 69 21)
							else
								(Print 69 22 #at -1 10)
							)
						)
						((Said 'open/casket,lid')
							(cond 
								((not climbedDown) (NotClose))
								((coffinLid cel?) (Print 69 23 #at -1 10))
								(else (Print 69 2))
							)
						)
						((Said 'get,move/casket') (if climbedDown (Print 69 24) else (NotClose)))
						((Said 'converse') (if mummyAwake (Print 69 25) else (Print 69 26)))
						((Said '/mummy>')
							(cond 
								((not mummyAwake) (event claimed: TRUE) (Print 69 11))
								((Said 'kill') (Print 69 27))
								((Said 'get,capture') (Print 69 28))
								((Said 'kiss') (Print 69 29))
								((Said 'help,,') (Print 69 30))
							)
						)
						((Said 'deliver/*/mummy') (if mummyAwake (Print 69 31) else (Print 69 11)))
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(NormalEgo)
		(super newRoom: newRoomNumber)
	)
)

(instance doMummy of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (self state?) 2)
				(< (ego distanceTo: mummy) 25)
			)
			(mummy setMotion: 0)
			(self changeState: 3)
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (ego inRect: 138 110 192 125))
					(Print 69 32 #at -1 10 #time 3)
					(ego observeBlocks: mummyBlock)
					(coffinLid priority: 8)
					(= mummyAwake TRUE)
					(mummy show:)
					(coffinLid setCycle: EndLoop self)
				else
					(-- state)
					(= seconds 5)
				)
			)
			(1 (mummy setCycle: EndLoop self))
			(2
				(mummy
					view: 196
					setCycle: Walk
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					setPri: 9
					setStep: 3 2
					setMotion: MoveTo 145 135 self
				)
				(mummyMusic play:)
			)
			(3
				(coffinLid ignoreActors: 0 stopUpd:)
				(if (ego has: iScarab)
					(Print 69 33 #at -1 10 #time 4)
					(self changeState: 5)
				else
					;EO: Seems like getting here without the scarab is not intended,
					;as the message questions you for that. Still, it doesn't hurt to have
					;behavior coded for that.
					(Print 69 34 #at -1 10 #time 5)
					(self changeState: 20)
				)
			)
			(5
				(mummy
					setPri: 9
					ignoreActors: 1
					setMotion: MoveTo (coffin x?) (coffin y?) self
				)
			)
			(6
				(mummy view: 195 setPri: 7 loop: 0 setCycle: BegLoop self)
			)
			(7
				(coffinLid ignoreActors: 1 setCycle: BegLoop self)
			)
			(8
				(mummy hide:)
				(= mummyAwake FALSE)
				(mummyMusic dispose:)
				(coffinLid setPri: 7 stopUpd:)
				(= state -1)
				(client setScript: 0)
				(ego ignoreBlocks: mummyBlock)
			)
			(20
				(mummy moveSpeed: 0 setMotion: Chase ego 15 self)
			)
			(21 (Print 69 35) (= dead TRUE))
		)
	)
)

(instance DownLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego setMotion: 0)
				(self cue:)
			)
			(1
				(ego
					view: 38
					setLoop: 0
					illegalBits: 0
					posn: 98 87
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(ego
					view: 39
					x: 98
					y: (+ (ego y?) 35)
					setLoop: 1
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Reverse
					setMotion: MoveTo 98 150 self
				)
			)
			(4
				(ego loop: 0 posn: 104 149 setCycle: BegLoop self)
			)
			(5
				(ego
					view: 4
					posn: 107 149
					setLoop: -1
					cycleSpeed: 0
					moveSpeed: 0
					loop: 1
					setPri: -1
					setCycle: Walk
					illegalBits: cWHITE
					ignoreActors: 0
				)
				(self cue:)
			)
			(6
				(= climbedDown TRUE)
				(= climbingLadder FALSE)
				(ego observeBlocks: ladderSpace)
				(User canControl: TRUE canInput: TRUE)
				(self cue:)
			)
			(7
				(if (== (mummy script?) 0)
					(mummy setScript: doMummy)
				)
			)
			(14
				(User canControl: FALSE canInput: FALSE)
				(ego
					view: 39
					setLoop: 0
					setMotion: 0
					illegalBits: 0
					posn: 107 151
					script: DownLadder
					setCycle: EndLoop self
					forceUpd:
				)
			)
			(15
				(ego
					view: 39
					x: 98
					setPri: 11
					setLoop: 1
					cel: 0
					cycleSpeed: 0
					moveSpeed: 0
					ignoreActors: 1
					setCycle: Walk
					setMotion: MoveTo (+ (ropeLadder x?) 0) (+ (ropeLadder y?) 35) self
				)
			)
			(16
				(ego
					view: 38
					posn: 96 (ropeLadder y?)
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: 1
					setCycle: BegLoop self
				)
			)
			(17 (self cue:))
			(18
				(ego
					view: 4
					posn: 80 87
					setLoop: -1
					setCycle: Walk
					illegalBits: cWHITE
				)
				(User canControl: TRUE canInput: TRUE)
				(= state -1)
				(= climbingLadder FALSE)
				(= climbedDown FALSE)
				(ego setScript: 0)
			)
		)
	)
)

(instance DropRope of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 70 88 self)
			)
			(1
				(ego view: 37 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(ropeLadder cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(ropeLadder startUpd:)	;so that the ladder doesn't disappear
				;(ropeLadder stopUpd:)
				(= ropeLadderLowered TRUE)
				(ego view: 41 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(4
				(ego view: 4 setLoop: -1 setCycle: Walk)
				(User canControl: TRUE canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance cryptFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					ignoreActors: 1
					illegalBits: 0
					setPri: (if (< (ego y?) 84) -1 else 12)
				)
				(self cue:)
			)
			(1
				(fallMusic play:)
				(ego view: 44 moveSpeed: 0 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 2 xStep: 10 yStep: 10 setCycle: Forward)
				(if (< (ego y?) 84)
					(ego
						setMotion: MoveTo (+ (ego x?) 10) (+ (ego y?) 60) self
					)
				else
					(ego
						setMotion: MoveTo (+ (ego x?) 10) (+ (ego y?) 70) self
					)
				)
			)
			(3
				(ego view: 42 setLoop: 0 setCel: 0 setPri: -1)
				(RedrawCast)
				(fallMusic dispose:)
				(ShakeScreen 5)
				(= seconds 1)
			)
			(4
				(Print 69 36 #width 100 #at -1 10)
				(= seconds 4)
			)
			(5
				(client setScript: 0)
				(= dead TRUE)
			)
		)
	)
)

(instance stoop of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 40 cel: 255 setCycle: EndLoop self)
				(if (> (ego x?) (pandoraBox x?))
					(ego loop: 1)
				else
					(ego loop: 0)
				)
			)
			(1
				(ego get: iPandorasBox)
				(pandoraBox dispose:)
				(if (not gotPandorasBox)
					(= gotPandorasBox TRUE)
					(= gotItem TRUE)
					(theGame changeScore: 4)
				)
				(if (== lolotteAlive FALSE) (theGame changeScore: -2))
				(ego setCycle: BegLoop self)
			)
			(2
				(ego view: 4 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance putBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 40 cel: 255 setCycle: EndLoop self)
				(if (> (ego x?) 160) (ego loop: 1) else (ego loop: 0))
			)
			(1
				(ego put: 4 69)
				(if (== lolotteAlive FALSE)
					(sounds eachElementDo: #stop 0)
					((Sound new:) number: 50 play:)
					(theGame changeScore: 2)
				)
				((= pandoraBox (Actor new:))
					view: 519
					posn:
						(if (> (ego x?) 160)
							(- (ego x?) 20)
						else
							(+ (ego x?) 20)
						)
						(ego y?)
					init:
				)
				(ego setCycle: BegLoop self)
			)
			(2
				(ego view: 4 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
