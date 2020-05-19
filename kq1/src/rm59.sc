;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include game.sh)
(use Main)
(use Intrface)
(use rgClouds)
(use LoadMany)
(use Motion)
(use User)
(use System)

(public
	rm59 0
)

(procedure (ExitCave)
	(if (Btst fInvisible)
		(ego view: 42)
	else
		(ego view: 56)
	)
)

(instance rm59 of cloudRoom
	(properties
		picture 59
		horizon 102
		east 69
		south 62
		west 58
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(LoadMany VIEW 42 56)
		(super init:)
		(Bset fLittleEgo)
		(ego init:)
		(NormalEgo)
		(switch prevRoomNum
			(south
				(ego posn: (proc0_17 242 (ego x?) 38) 188)
			)
			(west
				(ego posn: 26 (proc0_17 175 (ego y?) (+ horizon 2)))
			)
			(else 
				(curRoom setScript: fadeIn)
			)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((and (& (ego onControl: origin) cYELLOW) (< (ego y?) 90))
				((ScriptID 0 23) loop: 1 fade:)
				(self newRoom: east)
			)
			((& (ego onControl: origin) cYELLOW)
				(self setScript: fadeOut)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(Bclr fLittleEgo)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check[<at,around][/room]')
				(Print 59 0)
			)
			((Said 'look,check<in/cave')
				(if (ego inRect: 195 95 230 120)
					(Print 59 1)
				else
					(Print 59 2)
				)
			)
			((or (Said 'look,check/cave') (MouseClaimed event 201 68 226 101))
				(Print 59 3)
			)
			(
				(or
					(Said 'look,check/boulder')
					(MouseClaimed event 205 74 305 130)
					(MouseClaimed event 174 52 257 73)
					(MouseClaimed event 136 75 205 105)
					(MouseClaimed event 260 131 307 175)
					(MouseClaimed event 164 65 205 76)
					(MouseClaimed event 115 102 134 111)
				)
				(Print 59 4)
			)
			(
				(or
					(Said 'look,check/ceder')
					(MouseClaimed event 112 29 135 48)
					(MouseClaimed event 0 0 169 21)
					(MouseClaimed event 0 22 86 53)
					(MouseClaimed event 0 54 26 94)
					(MouseClaimed event 0 95 33 180)
					(MouseClaimed event 204 0 271 49)
					(MouseClaimed event 299 50 320 173)
				)
				(Print 59 5)
			)
			(
				(or
					(Said 'look,check/cloud')
					(MouseClaimed event 150 15 203 48)
					(MouseClaimed event 192 173 243 183)
					(MouseClaimed event 266 183 320 189)
				)
				(Print 59 6)
			)
		)
	)
)

(instance fadeOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ExitCave)
				(ego
					illegalBits: 0
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego loop: 0 cel: 0 setMotion: MoveTo 245 87 self)
			)
			(2
				(NormalEgo)
				(ego setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fadeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 7 posn: 218 100)
				(self cue:)
			)
			(1
				(ExitCave)
				(ego
					illegalBits: 0
					setLoop: 0
					cel: 0
					setCycle: 0
					setMotion: MoveTo 213 109 self
				)
			)
			(2
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: -1 setPri: -1 loop: 2)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
