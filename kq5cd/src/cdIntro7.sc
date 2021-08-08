;;; Sierra Script 1.0 - (do not remove this comment)
(script# 656)
(include game.sh)
(use Main)
(use AudioScript)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)

(public
	cdIntro7 0
)

(instance cdIntro7 of Room
	(properties
		picture 73
		style FADEOUT
	)
	
	(method (init)
		(self setScript: egoFlyingScript setRegions: 769)
		(User canInput: FALSE controls: FALSE)
		(Load PICTURE 73)
		(syncIt init: hide: play: 10111 658)
		(super init:)
		(UnLoad PICTURE 68)
		(Load PICTURE 74)
		(LoadMany VIEW 750 760 751)
		(ego
			view: 750
			setLoop: 3
			posn: 292 210
			setStep: 3 2
			normal: 0
			init:
		)
		((ego head?) hide:)
		(myWater init: setCycle: Forward)
		(owl init: setStep: 1 1 posn: 207 211)
	)
	
	(method (newRoom n)
		(ego setStep: -1 -1)
		(super newRoom: n)
	)
)

(instance owlFlyingScript of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(owl
					setLoop: 6
					cycleSpeed: 2
					setCycle: Forward
					setMotion: MoveTo 184 70 self
				)
			)
			(2 (owl setCycle: EndLoop self))
			(3
				(owl
					setLoop: 7
					setCycle: EndLoop self
					setMotion: MoveTo 140 70
				)
			)
			(4 (client setScript: 0))
		)
	)
)

(instance owl of Actor
	(properties
		view 760
	)
)

(instance egoFlyingScript of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(owl setScript: owlFlyingScript)
				(if (== howFast 2)
					(= seconds 7)
				else
					(= seconds 5)
				)
			)
			(1
				(ego setCycle: Forward setMotion: MoveTo 280 176 self)
			)
			(2
				(ego
					setCel: 0
					setCycle: Forward
					setLoop: 4
					setMotion: MoveTo 210 80 self
				)
			)
			(3
				(ego
					setCel: 0
					setLoop: 5
					setMotion: MoveTo 140 70
					setCycle: EndLoop self
				)
			)
			(4 (= waitForCue 16644))
			(5
				(ego setMotion: 0 hide:)
				(owl hide:)
				(myWater hide:)
				(= cycles 1)
			)
			(6
				(client setScript: a2s2Script)
			)
		)
	)
)

(instance a2s2Script of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 74 FADEOUT)
				((ego head?) hide:)
				(owl
					show:
					view: 760
					signal: ignrAct
					cycleSpeed: 2
					illegalBits: 0
					setLoop: 8
					setCycle: Forward
					posn: 247 207
				)
				(ego
					view: 750
					setLoop: 6
					normal: 0
					posn: 163 224
					cycleSpeed: 2
					setCycle: Forward
					show:
				)
				(= waitForCue 16724)
			)
			(1
				(owl setScript: owlFlying)
				(= seconds 5)
			)
			(2
				(ego setCycle: Forward setMotion: MoveTo 143 85)
				(= waitForCue 17668)
			)
			(3
				(ego
					setLoop: 7
					moveSpeed: 0
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(4
				(ego
					setLoop: 8
					setMotion: MoveTo 138 88
					setCycle: EndLoop self
				)
			)
			(5
				(ego setMotion: 0)
				(if (> (DoAudio Loc) -1) (-- state))
				(= cycles 1)
			)
			(6 (curRoom newRoom: 658))
		)
	)
)

(instance owlFlying of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(owl setMotion: MoveTo 254 90)
				(= waitForCue 16900)
			)
			(1 (owl setCycle: EndLoop self))
			(2
				(owl setLoop: 9 setCycle: EndLoop self)
			)
			(3
				(owl setLoop: 10 moveSpeed: 1 setCycle: Forward)
				(= waitForCue 17156)
			)
			(4
				(cls)
				(owl setCycle: EndLoop self)
			)
			(5
				(owl setLoop: 11 setCycle: EndLoop self)
			)
			(6
				(owl
					setLoop: 12
					moveSpeed: 0
					setMotion: MoveTo 133 90
					setCycle: EndLoop self
				)
			)
			(7
				(owl
					setLoop: 13
					setCycle: EndLoop
					setMotion: MoveTo 133 90 self
				)
			)
			(8 (owl dispose:))
		)
	)
)

(instance myWater of Prop
	(properties
		x 157
		y 148
		view 751
		cycleSpeed 5
	)
)

(instance syncIt of MonoAudioProp)
