;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm001 0
)

(local
	saveBits
)
(instance rm001 of Room
	(properties
		picture 1
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(= global157 1)
		(Load SOUND 1)
		(Load SOUND 2)
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
)

(instance openingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(curRoom overlay: 926 3)
				(= cycles 2)
			)
			(2
				(drip1 init:)
				(drip2 init:)
				(drip3 init:)
				(drip4 init:)
				(= seconds 10)
			)
			(3
				(drip1 dispose:)
				(drip2 dispose:)
				(drip3 dispose:)
				(drip4 dispose:)
				(curRoom drawPic: 150 5)
				(= cycles 2)
			)
			(4
				(TheMenuBar state: TRUE draw:)
				(StatusLine enable:)
				(= saveBits
					(Display
						{It has been an indeterminate amount\nof time since Roger Wilco rocketed\naway from Vohaul's burning space\nfortress. Time stands still for our\nhero in suspended animation.}
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
			(5
				(Display 1 0
					p_restore saveBits
				)
				(= cycles 2)
			)
			(6
				(= saveBits
					(Display
						{Its engines long spent, the small\nescape pod drifts aimlessly through\nunfamiliar star fields, its course\naltered many times by small asteroids\nand space debris. Inside, Roger lies\nundisturbed in his sleep chamber....\n...but not for long.}
						p_width 250
						p_at 35 65
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= seconds 15)
			)
			(7
				(Display 1 0
					p_restore saveBits
				)
				(= cycles 2)
			)
			(8
				(Scott init: setCycle: EndLoop setMotion: MoveTo 52 37 self)
			)
			(9
				(Scott lastCel: stopUpd:)
				(Mark init: setCycle: EndLoop setMotion: MoveTo 268 37 self)
			)
			(10
				(Mark lastCel:)
				(= cycles 2)
			)
			(11
				(Mark stopUpd:)
				(= saveBits
					(Display
						{Meanwhile, the Two Guys From\nAndromeda are the victims of foul play.\nRuthlessly abducted, their fate may\nlie in Roger's hands. (Scary, isn't it?!)}
						p_width 250
						p_at 35 90
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(= seconds 18)
			)
			(12
				(Display 1 0 p_restore saveBits)
				(Scott dispose:)
				(Mark dispose:)
				(= cycles 2)
			)
			(13
				(music number: 2 play:)
				(pod init: setMotion: MoveTo 129 97 self)
			)
			(14
				(curRoom overlay: 151 6)
				(podOutline init: setCycle: Forward)
				(pod setMotion: MoveTo -54 97 self)
			)
			(15
				(podOutline dispose:)
				(pod dispose:)
				(= showStyle DISSOLVE)
				(curRoom drawPic: 150)
				(= cycles 3)
			)
			(16
				(curRoom overlay: 152)
				(= cycles 7)
			)
			(17
				(podImage init: setCycle: EndLoop self)
			)
			(18 (= cycles 2))
			(19
				(podImage stopUpd:)
				(scanner init: setCycle: Forward)
				(= seconds 3)
			)
			(20
				(lifeForm init: setCycle: Forward)
				(= seconds 5)
			)
			(21
				(scanner dispose:)
				(alienWord init:)
				(= seconds 3)
			)
			(22
				(alienText init: setMotion: MoveTo 221 127 self)
			)
			(23
				(alienText posn: 221 127 stopUpd:)
				(alienWord setCel: 1 stopUpd:)
				(= cycles 15)
			)
			(24
				(cast eachElementDo: #dispose)
				(= showStyle 0)
				(curRoom drawPic: 153)
				(pod3 init: setMotion: MoveTo 120 142 self)
				(light init:)
				(hand init:)
			)
			(25
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 154 3)
				(pod2 init: setMotion: MoveTo 160 169 self)
				(hatch init:)
			)
			(26
				(beam init: stopUpd:)
				(pod2 setStep: 1 1 setMotion: MoveTo 137 169 self)
			)
			(27 (= seconds 2))
			(28
				(pod2 setMotion: MoveTo 137 80 self)
			)
			(29 (= cycles 2))
			(30
				(beam dispose:)
				(hatchScript cue:)
			)
			(31
				(pod2 dispose:)
				(= seconds 2)
			)
			(32
				(Display 1 1
					p_width 250
					p_mode teJustCenter
					p_at 35 120
					p_font 300
					p_color vYELLOW
				)
				(= seconds 12)
			)
			(33
				(music stop:)
				(curRoom newRoom: 2)
			)
		)
	)
)

(instance hatchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hatch stopUpd:)
				(= seconds 5)
			)
			(1 (hatch setCycle: EndLoop self))
			(2 (hatch stopUpd:))
			(3
				(hatch setPri: 8 setCycle: BegLoop self)
			)
			(4
				(hatch stopUpd:)
				(openingScript cue:)
			)
		)
	)
)

(instance handScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(hand
					setLoop: 1
					setCel: 0
					setCycle: 0
					setMotion: MoveTo 121 191 self
				)
			)
			(2 (hand setCycle: EndLoop self))
			(3
				(light setCycle: Forward)
				(= seconds 2)
			)
			(4
				(hand setCel: 0 setMotion: MoveTo 169 191 self)
			)
			(5 (= cycles 15))
			(6 (hand setCycle: EndLoop self))
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
			setScript: hatchScript
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
			posn: 160 37
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
			posn: 160 37
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
			posn: 271 82
			setStep: 2 2
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
