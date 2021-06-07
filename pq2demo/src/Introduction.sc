;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Introduction 0
)

(local
	[policeText 10]
	[questText 10]
	numILeft
	numIRight
	[local22 7]
	local29
	local30
	local31
)
(procedure (IntroDisplay &tmp temp0 saveBits)
	(= local29 0)
	(= local30 0)
	(= saveBits
		(Display
			&rest
			101 teJustCenter ;EO: p_mode; SCICompanion will not compile if a define immediately follows &rest
			p_at 10 10
			p_width 300
			p_color 15
			p_save
		)
	)
	(repeat
		(if (== (++ local29) 70)
			(++ local30)
			(= local29 0)
		)
		(breakif (== local30 local31))
	)
	(Display 200 0 p_restore saveBits)
)

(instance Introduction of Room
	(properties
		style HSHUTTER
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(Load SOUND 1)
		(Load PICTURE 46)
		(Load PICTURE 885)
		(Load PICTURE 48)
		(Load VIEW 311)
		(Load VIEW 903)
		(Load VIEW 904)
		(Load VIEW 209)
		(Load VIEW 292)
		(Load VIEW 310)
		(Load VIEW 900)
		(Load VIEW 901)
		(Load VIEW 902)
		(curRoom drawPic: 0 HSHUTTER)
		(curRoom setScript: IntroScript)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance IntroScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((= [policeText 9] (View new:))
					view: 900
					loop: 0
					posn: 160 63
					setPri: 0
					init:
					ignoreActors:
					stopUpd:
				)
				(cSound number: 1 loop: 1 play:)
				(self cue:)
			)
			(1
				((= [policeText 0] (Prop new:))
					view: 900
					loop: 9
					cel: 2
					posn: 160 30
					setPri: 1
					init:
					ignoreActors:
					setCycle: BegLoop self
				)
			)
			(2
				((= [policeText 1] (View new:))
					view: 900
					loop: 8
					posn: 160 34
					setPri: 2
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(3
				((= [policeText 2] (View new:))
					view: 900
					loop: 7
					posn: 160 40
					setPri: 3
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(4
				((= [policeText 3] (View new:))
					view: 900
					loop: 6
					posn: 160 50
					setPri: 4
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(5
				([policeText 0] dispose:)
				((= [policeText 4] (View new:))
					view: 900
					loop: 5
					posn: 160 64
					setPri: 5
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(6
				([policeText 1] dispose:)
				((= [policeText 5] (View new:))
					view: 900
					loop: 4
					posn: 160 74
					setPri: 6
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(7
				([policeText 2] dispose:)
				((= [policeText 6] (View new:))
					view: 900
					loop: 3
					posn: 160 80
					setPri: 7
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(8
				([policeText 3] dispose:)
				((= [policeText 7] (View new:))
					view: 900
					loop: 2
					posn: 160 78
					setPri: 8
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(9
				([policeText 4] dispose:)
				((= [policeText 8] (View new:))
					view: 900
					loop: 1
					posn: 160 70
					setPri: 9
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(10
				([policeText 5] dispose:)
				([policeText 9] setPri: 10 addToPic:)
				(= cycles 1)
			)
			(11
				([policeText 6] dispose:)
				((= [questText 0] (Prop new:))
					view: 901
					loop: 9
					cel: 2
					posn: 160 180
					setPri: 1
					init:
					ignoreActors:
					setCycle: BegLoop self
				)
			)
			(12
				([policeText 7] dispose:)
				((= [questText 1] (View new:))
					view: 901
					loop: 8
					posn: 160 174
					setPri: 2
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(13
				([policeText 8] dispose:)
				((= [questText 2] (View new:))
					view: 901
					loop: 7
					posn: 160 165
					setPri: 3
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(14
				((= [questText 3] (View new:))
					view: 901
					loop: 6
					posn: 160 158
					setPri: 4
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(15
				([questText 0] dispose:)
				((= [questText 4] (View new:))
					view: 901
					loop: 5
					posn: 160 152
					setPri: 5
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(16
				([questText 1] dispose:)
				((= [questText 5] (View new:))
					view: 901
					loop: 4
					posn: 160 144
					setPri: 6
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(17
				([questText 2] dispose:)
				((= [questText 6] (View new:))
					view: 901
					loop: 3
					posn: 160 134
					setPri: 7
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(18
				([questText 3] dispose:)
				((= [questText 7] (View new:))
					view: 901
					loop: 2
					posn: 160 126
					setPri: 8
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(19
				([questText 4] dispose:)
				((= [questText 8] (View new:))
					view: 901
					loop: 1
					posn: 160 120
					setPri: 9
					init:
					ignoreActors:
				)
				(= cycles 1)
			)
			(20
				([questText 5] dispose:)
				((= [questText 9] (View new:))
					view: 901
					loop: 0
					posn: 160 112
					setPri: 10
					init:
					ignoreActors:
					stopUpd:
					addToPic:
				)
				(= cycles 1)
			)
			(21
				([questText 6] dispose:)
				(= cycles 1)
			)
			(22
				([questText 7] dispose:)
				(= cycles 1)
			)
			(23
				([questText 8] dispose:)
				(= cycles 1)
			)
			(24
				((= numILeft (Actor new:))
					view: 902
					loop: 1
					cel: 0
					posn: 0 178
					setPri: 15
					init:
					setStep: 8
					ignoreActors:
					setCycle: 0
					setMotion: MoveTo 144 178
				)
				((= numIRight (Actor new:))
					view: 902
					loop: 0
					cel: 0
					posn: 320 178
					setPri: 15
					init:
					setStep: 8
					ignoreActors:
					setCycle: 0
					setMotion: MoveTo 176 178 self
				)
			)
			(25
				(numILeft setStep: 2 setMotion: MoveTo 140 178)
				(numIRight setStep: 2 setMotion: MoveTo 180 178 self)
			)
			(26
				(numILeft setMotion: MoveTo 143 178 setStep: 1)
				(numIRight setMotion: MoveTo 177 178 setStep: 1)
				(= seconds 3)
			)
			(27
				(numILeft dispose:)
				(numIRight dispose:)
				(curRoom setScript: everyoneElseScript)
			)
		)
	)
)

(instance bainsRunning of Actor)

(instance bainsShadow of Actor)

(instance sonnyRunning of Actor)

(instance sonnyShadow of Actor)

(instance everyoneElseScript of Script
	
	(method (init)
		(curRoom drawPic: 885)
		(bainsRunning
			view: 209
			setLoop: 1
			posn: -30 140
			setCycle: Walk
			setStep: 11 1
			init:
		)
		(bainsShadow
			view: 209
			setLoop: 2
			posn: -40 135
			setCycle: Walk
			setStep: 11 1
			init:
			ignoreActors: 1
			setPri: 1
		)
		(sonnyRunning
			view: 292
			setLoop: 1
			posn: -30 140
			setCycle: Walk
			setStep: 8 1
			init:
		)
		(sonnyShadow
			view: 292
			setLoop: 2
			posn: -40 135
			setCycle: Walk
			setStep: 8 1
			ignoreActors: 1
			setPri: 1
			init:
		)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bainsScript changeState: 0)
				(= seconds 10)
			)
			(1
				(curRoom drawPic: 48)
				(= cycles 10)
			)
			(2
				(= local31 3000)
				(IntroDisplay 200 1)
				(= local31 1500)
				(IntroDisplay 200 2)
				(self cue:)
			)
			(3 (curRoom newRoom: 4))
		)
	)
)

(instance bainsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bainsRunning init: setMotion: MoveTo 172 140 self)
				(bainsShadow init: setMotion: MoveTo 162 135)
			)
			(1
				(bainsShadow
					cel: (bainsShadow cel?)
					setMotion: MoveTo 235 135
				)
				(bainsRunning setLoop: 0 cel: (bainsRunning cel?))
				(bainsRunning setMotion: MoveTo 245 140 self)
				(sonnyScript changeState: 0)
			)
			(2
				(bainsShadow
					cel: (bainsShadow cel?)
					setMotion: MoveTo 330 135
				)
				(bainsRunning setLoop: 1 cel: (bainsRunning cel?))
				(bainsRunning setMotion: MoveTo 340 140 self)
			)
			(3
				(bainsRunning dispose:)
				(bainsShadow dispose:)
			)
		)
	)
)

(instance sonnyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sonnyRunning setMotion: MoveTo 172 140 self)
				(sonnyShadow setMotion: MoveTo 162 135)
			)
			(1
				(sonnyShadow
					cel: (sonnyShadow cel?)
					setMotion: MoveTo 230 135
				)
				(sonnyRunning setMotion: MoveTo 240 140 self)
				(sonnyRunning setLoop: 0 cel: (sonnyRunning cel?))
			)
			(2
				(sonnyShadow
					cel: (sonnyShadow cel?)
					setMotion: MoveTo 330 135
				)
				(sonnyRunning setMotion: MoveTo 340 140 self)
				(sonnyRunning setLoop: 1 cel: (sonnyRunning cel?))
			)
			(3
				(sonnyRunning dispose:)
				(sonnyShadow dispose:)
			)
		)
	)
)

(instance runNext of Script
	
	(method (init theSeconds)
		(= state 0)
		(= seconds theSeconds)
	)
)
