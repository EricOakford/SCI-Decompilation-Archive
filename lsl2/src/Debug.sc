;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use File)
(use Game)
(use User)
(use System)

(public
	rm5 0
)

(instance rm5 of Locale
	(properties)
	
	(method (handleEvent event &tmp temp0 item temp2 newEvent temp4 [str2 50])
		(if
			(and
				debugging
				(not (event claimed?))
				(== keyDown (event type?))
			)
			(switch (event message?)
				(`@i (User canInput: TRUE))
				(`@z (= quit TRUE))
				(`@c
					(Show CMAP)
					(Print 5 0 #at 0 0)
					(Show VMAP)
				)
				(`@f
					(Print 5 1)
					(= debugOn (^ debugOn TRUE))
				)
				(`@m (theGame showMem:))
				(`@p (Show PMAP))
				(`@r
					(Print (Format @str 5 2 curRoomNum))
				)
				(`@v (Show VMAP))
			)
		)
		(if
			(and
				debugging
				(not (event claimed?))
				(== mouseDown (event type?))
			)
			(cond 
				((& (event modifiers?) ctrlDown)
					(event claimed: TRUE)
					(User canControl: TRUE)
					(while (!= 2 ((= newEvent (Event new:)) type?))
						(GlobalToLocal newEvent)
						(ego posn: (newEvent x?) (newEvent y?) setMotion: 0)
						(AnimateCast)
						(newEvent dispose:)
					)
					(newEvent dispose:)
				)
				((& (event modifiers?) shiftDown)
					(event claimed: TRUE)
					(= temp2
						(Print
							(Format @str 5 3 (event x?) (event y?))
							#at 150 100
							#font 999
							#dispose
						)
					)
					(while (!= 2 ((= newEvent (Event new:)) type?))
						(newEvent dispose:)
					)
					(temp2 dispose:)
					(newEvent dispose:)
				)
			)
			(if (event claimed?) (return TRUE))
		)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return (event claimed?))
		)
		(if
			(and
				(Said 'pitch>')
				(= item (inventory saidMe:))
			)
			(event claimed: TRUE)
			(if (not (item ownedBy: ego))
				(Print 5 4)
			else
				(Print 5 5)
				(item moveTo: -1)
			)
		)
		(if
			(and
				(Said 'get>')
				(= item (inventory saidMe:))
			)
			(event claimed: TRUE)
			(if (item ownedBy: ego)
				(Print 5 6)
			else
				(Print 5 5)
				(item moveTo: ego)
			)
		)
		(if (Said 'aid') (Print 5 7 #font 999))
		(if (Said 'tp')
			(NormalEgo)
			(curRoom newRoom: (GetNumber {Teleport to:}))
		)
		(if (Said 'look/memory') (theGame showMem:))
		(if (Said 'look/frag')
			(Print 5 1)
			(= debugOn (^ debugOn TRUE))
		)
		(if (Said 'airport/number')
			(Print (Format @str 5 2 curRoomNum))
		)
		(if (Said 'make/note')
			(= str 0)
			(= str2 0)
			(if (GetInput @str2 50 {Writing to "note.log"})
				(Format @str 5 8
					curRoomNum (ego view?) (ego x?) (ego y?)
					currentStatus
				)
				(File
					name: {note.log}
					write: @str @str2 {\0D\n}
					close:
				)
			)
		)
		(if (Said 'look/cord')
			(Print
				(Format @str 5 9
					(/ gamePhaseTime 600)
					(/ (mod gamePhaseTime 600) 10)
				)
			)
		)
		(if (Said 'look/priority') (Show PMAP))
		(if (Said 'look/control')
			(Show CMAP)
			(Animate (cast elements?))
			(while (== 0 ((= event (Event new: allEvents)) type?))
				(event dispose:)
			)
			(event dispose:)
			(Show VMAP)
		)
		(if (Said 'look/normal') (Show VMAP))
		(if (Said 'look/ego')
			(Print
				(Format
					@str
					{view: %d loop: %d cel: %d posn: %d %d pri: %d OnControl: $%x Origin on: $%x}
					(ego view?)
					(ego loop?)
					(ego cel?)
					(ego x?)
					(ego y?)
					(ego priority?)
					(ego onControl:)
					(ego onControl: origin)
				)
				#icon
				(ego view?)
				(ego loop?)
				(ego cel?)
			)
		)
		(return
			(if (and (Said 'look/grid') (Load PICTURE 999))
				(DrawPic 999 VSHUTTER TRUE)
			else
				0
			)
		)
	)
)
