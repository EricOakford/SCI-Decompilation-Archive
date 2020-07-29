;;; Sierra Script 1.0 - (do not remove this comment)
(script# 915)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm915 0
)

(local
	boobsShown
	local1
	talkTimer
	talkCount
	buttonDoCount
)
(instance rm915 of LLRoom
	(properties
		picture 915
	)
	
	(method (init)
		(Load VIEW 1915)
		(Load VIEW 915)
		(Load VIEW 916)
		(super init:)
		(chichis init:)
		(herEye init:)
		(herMouth init: hide:)
		(herButton init:)
		(boobs init: hide:)
		(HandsOn)
		(InFirstPerson 1)
		(User canControl: FALSE)
		(= talkTimer 30)
		(if (!= prevRoomNum 910)
			(theMusic number: 910 loop: -1 play:)
			(ego get: iGreenCard)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (!= local1 (GetTime SYSTIME1))
			(= local1 (GetTime SYSTIME1))
			(if (not (-- talkTimer))
				(HandsOff)
				(curRoom setScript: sBackToWork)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch (Random 1 2)
					(1 (TimePrint 915 0))
					(2 (TimePrint 915 1))
				)
			)
			(verbUse
				(switch theItem
					(iGreenCard
						(HandsOff)
						(SolvePuzzle 15)
						(curRoom setScript: sGiveGreen)
					)
					(else
						(TimePrint 915 2)
					)
				)
			)
			(verbTalk
				(HandsOff)
				(curRoom setScript: sTalk)
			)
			(verbZipper
				(switch (Random 1 2)
					(1
						(Say Chi_Chi_Lambada 915 3 #dispose)
					)
					(2
						(Say Chi_Chi_Lambada 915 4 #dispose)
					)
				)
			)
			(verbDo
				(TimePrint 915 0)
			)
			(verbWalk
				(HandsOff)
				(Say ego 915 5)
				(theMusic fade:)
				(Bset fDentistClosed)
				(curRoom newRoom: 905)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= talkTimer 1000)
				(= cycles 2)
			)
			(1
				(switch (++ talkCount)
					(1 (Say ego 915 6 #dispose self))
					(2 (Say ego 915 7 #dispose self))
					(3
						(Say Chi_Chi_Lambada 915 8 #dispose #caller self)
					)
					(4
						(TimePrint 915 9 #at -1 185 #dispose self)
					)
					(5
						(Say Chi_Chi_Lambada 915 10 #dispose #caller self)
					)
				)
			)
			(2 (= ticks 30))
			(3
				(switch talkCount
					(1
						(Say Chi_Chi_Lambada 915 11 #dispose)
						(= ticks 123)
					)
					(2
						(Say Chi_Chi_Lambada 915 12 #dispose)
						(= ticks 123)
					)
					(3 (Say ego 915 13 #dispose self))
					(4
						(Say Chi_Chi_Lambada 915 14 #dispose)
						(= ticks 123)
					)
					(5 (Say ego 915 15 #dispose self))
				)
			)
			(4
				(switch talkCount
					(1
						(TimePrint 915 16 #at -1 185 #dispose self)
					)
					(2
						(HandsOn)
						(User canControl: FALSE)
						(= talkTimer 30)
					)
					(3
						(Say Chi_Chi_Lambada 915 17 #dispose)
						(= ticks 123)
					)
					(4 (Say ego 915 18 #dispose self))
					(5
						(Bset fGaveGreenCard)
						(SolvePuzzle 5)
						(curRoom newRoom: 910)
					)
				)
			)
			(5
				(switch talkCount
					(1
						(HandsOn)
						(User canControl: FALSE)
						(= talkTimer 30)
					)
					(3 (Say ego 915 19 #dispose self))
					(4
						(HandsOn)
						(User canControl: FALSE)
						(= talkTimer 30)
					)
				)
			)
			(6
				(Say Chi_Chi_Lambada 915 20 #dispose #caller self)
			)
			(7 (= ticks 60))
			(8
				(Say Chi_Chi_Lambada 915 21 #dispose #caller self)
			)
			(9 (= ticks 60))
			(10 (Say ego 915 22 #dispose self))
			(11
				(HandsOn)
				(User canControl: FALSE)
				(= talkTimer 30)
			)
		)
	)
)

(instance chichis of Feature
	(properties
		x 163
		y 255
		z 100
		nsTop 137
		nsLeft 113
		nsBottom 173
		nsRight 213
		description {her chi chis}
		sightAngle 40
	)
	
	(method (doVerb)
		(curRoom doVerb: &rest)
	)
)

(instance herButton of Feature
	(properties
		x 158
		y 338
		z 200
		nsTop 131
		nsLeft 149
		nsBottom 146
		nsRight 167
		description {her button}
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb verbDo)
				(if (not boobsShown)
					(if (< (++ buttonDoCount) 6)
						(HandsOff)
						(curRoom setScript: sStopThat)
					else
						(HandsOff)
						(theIconBar disable:)
						(TimePrint 915 23)
						(SolvePuzzle 2 fSeeChiChiBoobs)
						(boobs show:)
						(= boobsShown TRUE)
						(StartTimer 3 2 boobs)
					)
				)
			)
			((== theVerb verbLook)
				(TimePrint 915 24)
			)
			(else
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance boobs of Prop
	(properties
		x 160
		y 172
		description {her chi chis}
		sightAngle 90
		view 916
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb)
		(curRoom doVerb: &rest)
	)
	
	(method (cue)
		(curRoom setScript: sHideBoobs)
	)
)

(instance Chi_Chi_Lambada of Talker
	(properties
		x 50
		y 140
		nsTop 68
		nsLeft 151
		view 1915
		loop 3
		talkWidth 220
		name "Chi Chi Lambada"
	)
	
	(method (init)
		(= mouth talkerMouth)
		(super init:)
	)
)

(instance talkerMouth of Prop
	(properties
		view 1915
	)
)

(instance herEye of Prop
	(properties
		x 170
		y 55
		description {her eyes}
		sightAngle 90
		view 915
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb &rest)
	)
)

(instance herMouth of Prop
	(properties
		x 157
		y 169
		z 100
		description {her mouth}
		sightAngle 90
		view 915
		loop 2
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance sBackToWork of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= talkTimer 1000)
				(= cycles 2)
			)
			(1
				(Say Chi_Chi_Lambada 915 25 #dispose #caller self)
			)
			(2 (= ticks 30))
			(3 (herEye setCycle: EndLoop self))
			(4 (= ticks 60))
			(5 (herEye setCycle: BegLoop self))
			(6 (= ticks 30))
			(7 (curRoom newRoom: 910))
		)
	)
)

(instance sHideBoobs of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= talkTimer 1000)
				(= buttonDoCount 0)
				(= boobsShown FALSE)
				(= cycles 2)
			)
			(1
				(Say Chi_Chi_Lambada 915 26 #dispose #caller self)
			)
			(2
				(boobs hide:)
				(theIconBar enable:)
				(= ticks 90)
			)
			(3 (= seconds 2))
			(4 (herEye setCycle: EndLoop self))
			(5 (= ticks 60))
			(6 (herEye setCycle: BegLoop self))
			(7 (= ticks 30))
			(8
				(HandsOn)
				(= talkTimer 20)
				(self dispose:)
			)
		)
	)
)

(instance sStopThat of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= talkTimer 1000)
				(= cycles 2)
			)
			(1
				(Say Chi_Chi_Lambada 915 27 #dispose #caller self)
			)
			(2 (= ticks 20))
			(3
				(herMouth show: setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(4 (= ticks 60))
			(5
				(herMouth setCycle: BegLoop self)
			)
			(6
				(herMouth hide:)
				(= talkTimer 20)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGiveGreen of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= talkTimer 1000)
				(= cycles 2)
			)
			(1 (Say ego 915 28 #dispose self))
			(2 (= ticks 60))
			(3
				(Say Chi_Chi_Lambada 915 29 #dispose #caller self)
			)
			(4
				(herMouth dispose:)
				(herEye dispose:)
				(= ticks 60)
			)
			(5
				(ego put: iGreenCard)
				(Bset fGaveGreenCard)
				(curRoom newRoom: 910)
			)
		)
	)
)
