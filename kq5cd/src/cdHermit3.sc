;;; Sierra Script 1.0 - (do not remove this comment)
(script# 662)
(include game.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	cdHermit3 0
)

(local
	saveSpeed
)
(instance cdHermit3 of KQ5Room
	(properties
		picture 88
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(= saveSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 1)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(Load SOUND 820)
		(Load VIEW 827)
		(Load VIEW 625)
		(Load VIEW 618)
		(Load 142 1142)
		(Load 142 1143)
		(Load 142 1144)
		(Load 142 1145)
		(Load 142 1146)
		(if (!= (theMusic number?) 820)
			(theMusic number: 820 loop: -1 vol: 127 playBed:)
		)
		(if (== gGNumber_2 46)
			(sailBoat
				init:
				y: (if (== curRoomNum 44) 153 else 173)
				stopUpd:
				ignoreActors:
			)
			(sail
				posn: (+ (sailBoat x?) 7) (sailBoat y?)
				setPri: (sailBoat priority?)
				init:
			)
		)
		(egoMouth init:)
		(hermit_eyes init:)
		(chimney setCycle: Forward init:)
		(self setScript: cartoon2)
	)
	
	(method (dispose)
		(theGame egoMoveSpeed: saveSpeed)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance cartoon2 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(hermit_eyes cycleSpeed: 20 setCycle: RandCycle)
				(theMouth changeMouth: 1 setCycle: MouthSync 1142 init:)
				(SpeakAudio 1142 self)
			)
			(2
				(theMouth setCycle: 0 hide:)
				(= cycles 1)
			)
			(3
				(egoMouth hide:)
				(theMouth changeMouth: 0 setCycle: MouthSync 1143 show:)
				(SpeakAudio 1143 self)
			)
			(4
				(theMouth setCycle: 0 hide:)
				(egoMouth show:)
				(= cycles 1)
			)
			(5
				(theMouth changeMouth: 1 setCycle: MouthSync 1144 show:)
				(SpeakAudio 1144 self)
			)
			(6
				(theMouth setCycle: 0)
				(= seconds 1)
			)
			(7
				(theMouth setCycle: MouthSync 1145)
				(SpeakAudio 1145 self)
			)
			(8
				(theMouth setCycle: 0)
				(= seconds 2)
			)
			(9
				(theMouth setCycle: MouthSync 1146)
				(SpeakAudio 1146 self)
			)
			(10
				(theMouth setCycle: 0 hide:)
				(theMusic fade:)
				(if (Btst fCedricInjured)
					(curRoom newRoom: 660)
				else
					(curRoom newRoom: 661)
				)
			)
		)
	)
)

(instance theMouth of Prop
	(properties
		x 135
		y 104
		view 827
		loop 2
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth x: 183 y: 85 view: 827 loop: 0)
			)
			(1
				(theMouth x: 135 y: 104 view: 827 loop: 2)
			)
		)
	)
)

(instance egoMouth of Prop
	(properties
		x 183
		y 85
		view 827
	)
)

(instance hermit_eyes of Prop
	(properties
		x 144
		y 83
		view 827
		loop 3
	)
)

(instance chimney of Prop
	(properties
		x 18
		y 70
		z 20
		view 625
		loop 2
		cycleSpeed 3
		detailLevel 3
	)
)

(instance sailBoat of Actor
	(properties
		x 280
		y 173
		view 618
		cel 1
		priority -1
		signal (| ignrAct stopUpdOn)
	)
)

(instance sail of Actor
	(properties
		z 15
		view 618
		loop 2
		signal (| ignrAct stopUpdOn)
	)
)
