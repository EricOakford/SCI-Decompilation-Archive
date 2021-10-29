;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include menu.sh)
(use Main)
(use Sound)
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
	local22
	local23
	local24
	local25
	farX
	farY
	local28
)
(procedure (DoDisplay &tmp evt saveBits)
	(= saveBits
		(Display &rest
			101 teJustCenter	;101 should be p_mode
			p_at 10 10
			p_width 300
			p_color vWHITE
			p_save
		)
	)
	(while (not (& ((= evt (Event new: (| keyDown mouseDown))) type?) (| keyDown mouseDown)))
		(evt dispose:)
	)
	(evt dispose:)
	(Display 200 10 p_restore saveBits)
)

(instance introMusic of Sound
	(properties
		number 1
	)
)

(instance Introduction of Room
	(properties
		style HSHUTTER
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(= speed 6)	;added to fix speed bug
		(Load VIEW 900)
		(Load VIEW 901)
		(Load VIEW 902)
		(Load PICTURE 46)
		(Load SOUND 1)
		(Load VIEW 310)
		(Load VIEW 924)
		(Load VIEW 925)
		(Load PICTURE 47)
		(Load PICTURE 885)
		(Load PICTURE 48)
		(Load VIEW 311)
		(Load VIEW 903)
		(Load VIEW 904)
		(Load VIEW 209)
		(Load VIEW 292)
		(Load SOUND 1)
		(curRoom drawPic: 0 HSHUTTER)
		(curRoom setScript: IntroScript)
	)
	
	(method (dispose)
		;(sounds eachElementDo: #dispose) causes 'Memory Fragmented.'
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (== (event type?) keyDown)
			(event claimed: TRUE)
			(if (== (event message?) `#2)
				(if (GetMenu soundI p_value)
					(DoSound SoundOn FALSE)
					(SetMenu soundI
						p_value FALSE
						p_text {Turn on}
					)
				else
					(DoSound SoundOn TRUE)
					(SetMenu soundI
						p_value TRUE
						p_text {Turn off}
					)
				)
			)
			(if
				(or
					(== (event message?) ENTER)
					(and
						(>= (event message?) SPACEBAR)
						(<= (event message?) 96)
					)
				)
				(event claimed: TRUE)
				(curRoom newRoom: 701)
			)
		)
	)
)

(instance IntroScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(introMusic play:)
				(if (< howFast 30)
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
					((= [policeText 9] (View new:))
						view: 900
						loop: 0
						posn: 160 63
						setPri: 0
						init:
						ignoreActors:
						setPri: 10
						addToPic:
					)
					(self changeState: 24)
				else
					((= [policeText 9] (View new:))
						view: 900
						loop: 0
						posn: 160 63
						setPri: 0
						init:
						ignoreActors:
						stopUpd:
					)
					(self cue:)
				)
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
					posn: (if (< howFast 30) 120 else 0) 178
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
					posn: (if (< howFast 30) 200 else 320) 178
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
				(curRoom setScript: jimScript)
			)
		)
	)
)

(instance streetLight of Prop)

(instance redLight1 of Prop)

(instance redLight2 of Prop)

;(instance blueLight1 of Prop)

;(instance blueLight2 of Prop)

(instance building of Actor)

(instance farBuildings of Actor)

(instance jim1 of View)

(instance jim2 of View)

(instance jim3 of View)

(instance jimb1 of View)

(instance jimb2 of View)

(instance jimScript of Script
	(method (init)
		(runNext init: 16)
		(curRoom drawPic: 46)
		(= local23 0)
		(= local24 0)
		(= farX 337)
		(= farY 76)
		(streetLight
			view: 310
			loop: 2
			posn: 298 122
			setPri: 15
			init:
		)
		(redLight1
			view: 310
			loop: 1
			posn: 276 149
			setPri: 12
			init:
		)
		(redLight2
			view: 310
			loop: 1
			posn: 293 149
			setPri: 12
			init:
		)
		(building
			view: 310
			setLoop: 3
			setCel: 0
			posn: 340 80
			setPri: 1
			init:
			illegalBits: 0
			ignoreActors:
			setMotion: MoveTo 250 105
		)
		(farBuildings
			view: 310
			setLoop: 4
			setCel: 0
			posn: farX farY
			setPri: 0
			init:
		)
		(jim1
			view: 924
			loop: 0
			cel: 0
			posn: 26 108
			setPri: 14
			init:
			ignoreActors:
		)
		(jim2
			view: 924
			loop: 0
			cel: 1
			posn: 56 108
			setPri: 14
			init:
			ignoreActors:
		)
		(jim3
			view: 924
			loop: 0
			cel: 2
			posn: 80 108
			setPri: 14
			init:
			ignoreActors:
			addToPic:
		)
		(jimb1
			view: 925
			loop: 0
			cel: 0
			posn: 125 108
			setPri: 14
			init:
			ignoreActors:
			addToPic:
		)
		(jimb2
			view: 925
			loop: 0
			cel: 1
			posn: 176 108
			setPri: 14
			init:
			ignoreActors:
			addToPic:
		)
		(RedrawCast)
		(super init:)
	)
	
	(method (doit)
		(if (== local23 0) (self changeState: 0))
		(runNext doit:)
		(if (runNext state?)
			(runNext state: 0)
			(cast eachElementDo: #dispose)
			(curRoom setScript: everyoneElseScript)
		)
		(cond 
			((and (== local24 3) (= local25 0))
				(-= farX 1)
				(farBuildings posn: farX farY)
				(= local24 0)
				(+= local25 1)
			)
			((and (== local24 3) (= local25 10))
				(-= farX 1)
				(+= farY 1)
				(farBuildings posn: farX farY)
				(= local24 0)
				(= local25 0)
			)
			(else
				(+= local24 1)
			)
		)
		(if (and (== (building x?) 250) (== (building y?) 105))
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(streetLight setCycle: Forward)
				(redLight1 setCycle: Forward)
				(redLight2 setCycle: Forward)
				(Display 200 0
					p_at 10 115
				)
				(Display 200 1
					p_at 10 125
				)
				(Display 200 2
					p_at 10 135
				)
				(Display 200 3
					p_at 10 145
				)
				(Display 200 4
					p_at 10 155
				)
				(Display 200 5
					p_at 10 165
				)
				(Display 200 6
					p_at 150 115
				)
				(Display 200 7
					p_at 150 125
				)
				(Display 200 8
					p_at 175 135
				)
				(Display 200 9
					p_at 175 145
				)
				(= local23 1)
			)
			(1
				(building posn: 340 80 setMotion: MoveTo 250 105)
			)
		)
	)
)

(instance bainsRunning of Actor)

(instance bainsShadow of Actor)

(instance sonnyRunning of Actor)

(instance sonnyShadow of Actor)

(instance legs of Prop)

(instance weeds of Actor)

(instance weeds2 of Actor)

(instance bubbles of Actor)

(instance executive of View)

(instance producer of View)

(instance ken of View)

(instance programming of View)

(instance art of View)

(instance animation of View)

(instance project of View)

(instance coordinator of View)

(instance system of View)

(instance development of View)

(instance music of View)

(instance heitman of View)

(instance jeff of View)

(instance pablo of View)

(instance stuart of View)

(instance crowe of View)

(instance jerry of View)

(instance fischbach of View)

(instance mickie of View)

(instance dave of View)

(instance vu of View)

(instance cheri of View)

(instance chris of View)

(instance everyoneElseScript of Script
	(method (init)
		(curRoom drawPic: 47)
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
			ignoreActors: TRUE
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
			ignoreActors: TRUE
			setPri: 1
			init:
		)
		(legs
			view: 311
			loop: 0
			posn: 253 125
			setPri: 0
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		(weeds
			view: 311
			setLoop: 1
			posn: 10 130
			setPri: 2
			init:
			setCycle: Forward
			cycleSpeed: 1
			setStep: 4 1
			setMotion: MoveTo 300 130 weed1Script
		)
		(weeds2
			view: 311
			setLoop: 1
			posn: 150 140
			setPri: 2
			init:
			setCycle: Forward
			setStep: 4 1
			setMotion: MoveTo 300 140 weed2Script
		)
		(bubbles
			view: 311
			setLoop: 2
			posn: 60 130
			setPri: 1
			setStep: 4 5
			init:
			setCycle: Forward
			setMotion: MoveTo 160 0 bubbleScript
		)
		(super init:)
		(self changeState: 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(executive
					view: 903
					setLoop: 5
					setCel: 0
					ignoreActors:
					posn: 158 64
					init:
					stopUpd:
				)
				(producer
					view: 903
					setLoop: 5
					setCel: 1
					ignoreActors:
					posn: 158 82
					init:
					stopUpd:
				)
				(ken
					view: 904
					setLoop: 6
					setCel: 0
					ignoreActors:
					posn: 157 100
					init:
					stopUpd:
				)
				(= seconds 5)
			)
			(1
				(executive dispose:)
				(producer dispose:)
				(ken dispose:)
				(development
					view: 903
					setLoop: 3
					setCel: 1
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 158 64
					stopUpd:
				)
				(system
					view: 903
					setLoop: 3
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					posn: 153 82
					init:
					stopUpd:
				)
				(jeff
					view: 904
					setLoop: 0
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 157 97
					stopUpd:
				)
				(heitman
					view: 904
					setLoop: 0
					setCel: 1
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 158 115
					stopUpd:
				)
				(pablo
					view: 904
					setLoop: 0
					setCel: 2
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 158 131
					stopUpd:
				)
				(stuart
					view: 904
					setLoop: 0
					setCel: 3
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 159 148
					stopUpd:
				)
				(= seconds 8)
			)
			(2
				(system dispose:)
				(development dispose:)
				(heitman dispose:)
				(jeff dispose:)
				(pablo dispose:)
				(stuart dispose:)
				(project
					view: 903
					setLoop: 4
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 156 86
					stopUpd:
				)
				(coordinator
					view: 903
					setLoop: 4
					setCel: 1
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 157 104
					stopUpd:
				)
				(crowe
					view: 904
					setLoop: 1
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 155 121
					stopUpd:
				)
				(= seconds 4)
			)
			(3
				(project dispose:)
				(coordinator dispose:)
				(crowe dispose:)
				(legs dispose:)
				(weeds dispose:)
				(weeds2 dispose:)
				(bubbles dispose:)
				(curRoom drawPic: 885)
				(programming
					view: 903
					setLoop: 0
					setCel: 0
					setPri: 15
					init:
					posn: 97 20
					ignoreActors: TRUE
					stopUpd:
				)
				(dave
					view: 904
					setLoop: 2
					setCel: 2
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 38
					stopUpd:
				)
				(fischbach
					view: 904
					setLoop: 1
					setCel: 2
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 54
					stopUpd:
				)
				(jerry
					view: 904
					setLoop: 1
					setCel: 1
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 71
					stopUpd:
				)
				(mickie
					view: 904
					setLoop: 2
					setCel: 1
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 88
					stopUpd:
				)
				(heitman
					view: 904
					setLoop: 2
					setCel: 0
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 105
					stopUpd:
				)
				(vu
					view: 904
					setLoop: 5
					setCel: 0
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 97 121
					stopUpd:
				)
				(= seconds 2)
			)
			(4
				(bainsScript changeState: 0)
				(= seconds 10)
			)
			(5
				(programming dispose:)
				(jerry dispose:)
				(fischbach dispose:)
				(heitman dispose:)
				(mickie dispose:)
				(dave dispose:)
				(art
					view: 903
					setLoop: 1
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 38 50
					stopUpd:
				)
				(animation
					view: 903
					setLoop: 1
					setCel: 1
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 121 50
					stopUpd:
				)
				(cheri
					view: 904
					setLoop: 3
					setCel: 1
					ignoreActors: TRUE
					setPri: 3
					init:
					posn: 96 70
					stopUpd:
				)
				(vu setLoop: 3 setCel: 0 posn: 96 90)
				(= seconds 5)
			)
			(6
				(art dispose:)
				(animation dispose:)
				(vu dispose:)
				(cheri dispose:)
				(music
					view: 903
					setLoop: 2
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 97 50
					stopUpd:
				)
				(chris
					view: 904
					setLoop: 4
					setCel: 0
					ignoreActors: TRUE
					setPri: 15
					init:
					posn: 97 70
					stopUpd:
				)
				(= seconds 6)
			)
			(7
				(music dispose:)
				(chris dispose:)
				(curRoom drawPic: 48)
				(= cycles 10)
			)
			(8
				(DoDisplay 200 11)
				(DoDisplay 200 12)
				(curRoom newRoom: 701)
			)
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

(instance bubbleScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bubbles posn: 60 130 setMotion: MoveTo 160 0 self)
			)
			(1
				(self changeState: 0)
			)
		)
	)
)

(instance weed1Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(weeds posn: 10 130 setMotion: MoveTo 300 130 self)
			)
			(2
				(self changeState: 1)
			)
		)
	)
)

(instance weed2Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(3
				(weeds posn: 10 140 setMotion: MoveTo 300 140 self)
			)
			(4
				(self changeState: 1)
			)
		)
	)
)

(instance runNext of Script
	(method (init numSeconds)
		(= state 0)
		(= seconds numSeconds)
	)
)