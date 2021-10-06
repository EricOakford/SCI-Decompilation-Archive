;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm001 0
)

(local
	saveBits1
	saveBits2
	local2
)
(instance rm001 of Room
	(properties
		picture 1
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(Load SOUND 72)
		(Load SOUND 73)
		(Load SOUND 87)
		(Load PICTURE 1)
		(Load PICTURE 150)
		(Load PICTURE 151)
		(Load PICTURE 152)
		(Load PICTURE 153)
		(Load PICTURE 154)
		(Load PICTURE 926)
		(Load VIEW 1)
		(Load VIEW 2)
		(Load VIEW 3)
		(Load VIEW 4)
		(Load VIEW 5)
		(Load VIEW 601)
		(Load VIEW 926)
		(super init:)
		(self setScript: openingScript)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (event type?)
			(Display 1 0 p_restore saveBits1)
			(Display 1 0 p_restore saveBits2)
			(theMusic stop:)
			(curRoom newRoom: 2)
		)
	)
)

(instance openingScript of Script
	(method (doit)
		(if (and (== (theMusic prevSignal?) -1) (== local2 1))
			(= local2 0)
			(openingScript cue:)
		)
		(if (and (== local2 2) (== (beamSound prevSignal?) -1))
			(= local2 0)
			(curRoom newRoom: 155)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(curRoom overlay: 926 WIPERIGHT)
				(= cycles 2)
			)
			(2
				(drip1 init:)
				(drip2 init:)
				(drip3 init:)
				(drip4 init:)
				(= seconds 3)
			)
			(3
				(= saveBits1
					(Display {\04 1989 Sierra On-Line, Inc.}
						p_width 250
						p_at 35 180
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= seconds 7)
			)
			(4
				(drip1 dispose:)
				(drip2 dispose:)
				(drip3 dispose:)
				(drip4 dispose:)
				(Display 1 0 p_restore saveBits1)
				(curRoom drawPic: 150 WIPEDOWN)
				(= cycles 2)
			)
			(5
				(Mark init: setCycle: EndLoop setMotion: MoveTo 52 37 self)
			)
			(6
				(Mark stopUpd:)
				(Scott init: setCycle: EndLoop setMotion: MoveTo 268 37 self)
			)
			(7
				(= cycles 2)
			)
			(8
				(Scott stopUpd:)
				(= saveBits1
					(Display
						{Created and Written by}
						p_width 250
						p_at 30 90
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= saveBits2
					(Display
						{Mark Crowe and Scott Murphy}
						p_width 250
						p_at 30 105
						p_mode teJustCenter
						p_font 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 7)
			)
			(9
				(Scott dispose:)
				(Mark dispose:)
				(Display 1 0 p_restore saveBits1)
				(Display 1 0 p_restore saveBits2)
				(RedrawCast)
				(= saveBits2
					(Display
						{Programmed by\n\n\n
						Graphics by\n\n
						Game Development System by\n\n\n
						Music by\n\n
						Sound Effects & Music Editing by}
						p_width 250
						p_at 30 25
						p_mode teJustLeft
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(RedrawCast)
				(= saveBits1
					(Display
						{\nScott Murphy, Doug Oldfield,\n
						Ken Koch, Chris Smith\n\n
						Mark Crowe\n\n
						Jeff Stephenson, Bob Heitman,\n
						Pablo Ghenis, Stuart Goldstein\n\n
						Bob Siebenberg\n\n
						Mark Seibert}
						p_width 250
						p_at 80 25
						p_mode teJustLeft
						p_font 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 12)
			)
			(10
				(Display 1 0 p_restore saveBits1)
				(Display 1 0 p_restore saveBits2)
				(RedrawCast)
				(= saveBits1
					(Display
						{It has been an indeterminate amount\n
						of time since Roger Wilco rocketed\n
						away from Vohaul's burning space\n
						fortress. Time stands still for our\n
						hero in suspended animation.}
						p_width 250
						p_at 35 65
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= seconds 12)
			)
			(11
				(Display 1 0 p_restore saveBits1)
				(= cycles 2)
			)
			(12
				(= saveBits1
					(Display
						{Its engines long spent, the small\n
						escape pod drifts aimlessly through\n
						unfamiliar star fields, its course\n
						altered many times by small asteroids\n
						and space debris. Inside, Roger lies\n
						undisturbed in his sleep chamber....\n
						...but not for long.}
						p_width 250
						p_at 35 65
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= seconds 13)
			)
			(13
				(= local2 1)
			)
			(14
				(Display 1 0 p_restore saveBits1)
				(RedrawCast)
				(theMusic number: 72 play:)
				(pod init: setMotion: MoveTo 129 97 self)
			)
			(15
				(theMusic number: 73 loop: -1 play:)
				(curRoom overlay: 151 IRISIN)
				(podOutline init: setCycle: Forward)
				(pod setMotion: MoveTo -54 97 self)
			)
			(16
				(podOutline dispose:)
				(pod dispose:)
				(= showStyle 8)
				(curRoom drawPic: 150)
				(= cycles 3)
			)
			(17
				(curRoom overlay: 152)
				(= cycles 7)
			)
			(18
				(podImage init: setCycle: EndLoop self)
			)
			(19
				(= cycles 2)
			)
			(20
				(podImage stopUpd:)
				(scanner init: setCycle: Forward)
				(= seconds 3)
			)
			(21
				(lifeForm init: setCycle: Forward)
				(= seconds 5)
			)
			(22
				(scanner dispose:)
				(alienWord init:)
				(= seconds 3)
			)
			(23
				(alienText init: setMotion: MoveTo 221 127 self)
			)
			(24
				(alienText posn: 221 127 stopUpd:)
				(alienWord setCel: 1 stopUpd:)
				(= cycles 15)
			)
			(25
				(cast eachElementDo: #dispose)
				(= showStyle HSHUTTER)
				(curRoom drawPic: 153)
				(pod3 init: setMotion: MoveTo 87 156 self)
				(light init:)
				(hand init:)
			)
			(26
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 154 WIPERIGHT)
				(pod2 init: setMotion: MoveTo 184 169 self)
				(hatch init:)
			)
			(27
				(beamSound play:)
				(pod2 setStep: 1 1 setMotion: MoveTo 137 169 self)
				(= cycles 2)
			)
			(28
				(beam init: stopUpd:)
				(theMusic stop:)
			)
			(29
				(= cycles 20)
			)
			(30
				(pod2 setMotion: MoveTo 137 80 self)
			)
			(31
				(= cycles 2)
			)
			(32
				(beam dispose:)
				(self setScript: closeHatchScript self)
			)
			(33
				(pod2 dispose:)
				(= seconds 2)
			)
			(34
				(Display 1 1
					p_width 250
					p_mode teJustCenter
					p_at 35 120
					p_font 300
					p_color vYELLOW
				)
				(= seconds 12)
			)
			(35
				(= local2 2)
			)
		)
	)
)

(instance openHatchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hatch stopUpd:)
				(= seconds 5)
			)
			(1
				(hatch setCycle: EndLoop self)
			)
			(2
				(hatch stopUpd:)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance closeHatchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hatch setPri: 8 setCycle: BegLoop self)
			)
			(1
				(hatch stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance handScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(hand
					setLoop: 1
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 121 191 self
				)
			)
			(2
				(hand setCycle: EndLoop self)
			)
			(3
				(light setCycle: Forward)
				(= seconds 2)
			)
			(4
				(hand setCel: 0 setMotion: MoveTo 169 191 self)
			)
			(5
				(= cycles 15)
			)
			(6
				(hand setCycle: EndLoop self)
			)
			(7
				(hand setCel: 0 setMotion: MoveTo 121 223)
			)
		)
	)
)

(instance podOutline of Prop
	(properties
		view 1
		cel 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setLoop: 1
			setCel: 1
			setPri: 5
			posn: 127 97
		)
	)
)

(instance lifeForm of Prop
	(properties
		view 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE setLoop: 2 setPri: 5 posn: 152 86)
	)
)

(instance scanner of Prop
	(properties
		view 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE setLoop: 3 setPri: 5 posn: 105 111)
	)
)

(instance podImage of Prop
	(properties
		view 2
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setLoop: 1
			setPri: 5
			posn: 111 97
			cycleSpeed: 1
		)
	)
)

(instance hatch of Actor
	(properties
		view 3
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setLoop: 0
			setCel: 0
			setPri: 5
			posn: 108 102
			setScript: openHatchScript
		)
	)
)

(instance hand of Actor
	(properties
		view 5
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			posn: 121 223
			setLoop: 1
			setCel: 0
			setPri: 6
			setStep: 8 8
			setCycle: 0
			setScript: handScript
		)
	)
)

(instance Scott of Actor
	(properties
		view 601
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			ignoreActors: TRUE
			posn: 160 100
			setPri: 4
			setStep: 12 12
		)
	)
)

(instance Mark of Actor
	(properties
		view 601
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			ignoreActors: TRUE
			posn: 160 100
			setPri: 4
			setStep: 12 12
		)
	)
)

(instance drip1 of Prop
	(properties
		view 926
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 0
			posn: 141 112
			setPri: 15
			setCycle: Forward
		)
	)
)

(instance drip2 of Prop
	(properties
		view 926
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 9
			posn: 163 152
			setPri: 15
			setCycle: Forward
		)
	)
)

(instance drip3 of Prop
	(properties
		view 926
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 2
			posn: 216 148
			setPri: 15
			setCycle: Forward
		)
	)
)

(instance drip4 of Prop
	(properties
		view 926
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 4
			posn: 270 143
			setPri: 15
			setCycle: Forward
		)
	)
)

(instance drip5 of Prop
	(properties
		view 926
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 6
			posn: 34 145
			setPri: 15
			setCycle: Forward
		)
	)
)

(instance pod3 of Actor
	(properties
		view 5
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setLoop: 2
			setPri: 4
			posn: 191 52
			setStep: 5 2
		)
	)
)

(instance light of Prop
	(properties
		view 5
		priority 6
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: TRUE)
	)
)

(instance beam of View
	(properties
		view 4
	)
	
	(method (init)
		(super init:)
		(self posn: 110 189 setPri: 6)
	)
)

(instance pod2 of Actor
	(properties
		view 3
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			ignoreActors: TRUE
			illegalBits: 0
			posn: 320 169
			setPri: 7
			setStep: 2 1
		)
	)
)

(instance pod of Actor
	(properties
		view 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setLoop: 0
			setCel: 0
			setPri: 4
			posn: 363 97
			setStep: 2
		)
	)
)

(instance alienWord of Prop
	(properties
		view 2
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setLoop: 4
			setPri: 6
			posn: 226 144
			setCycle: Forward
		)
	)
)

(instance alienText of Actor
	(properties
		view 2
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			setLoop: 0
			setPri: 4
			posn: 221 205
			setStep: -1 6
		)
	)
)

(instance beamSound of Sound
	(properties
		number 87
		priority 5
	)
)
